package io.github.lostblackknight.admin.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.admin.support.CustomUserDetails;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.model.vo.form.UsernamePasswordLoginForm;
import io.github.lostblackknight.properties.JwtProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 自定义用户名密码认证过滤器
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final AntPathRequestMatcher ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/token", "POST");

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        setAuthenticationManager(authenticationManager);
        setRequiresAuthenticationRequestMatcher(ANT_PATH_REQUEST_MATCHER);

        final JwtProperties.AccessToken accessToken = jwtProperties.getAccessToken();
        final JwtProperties.RefreshToken refreshToken = jwtProperties.getRefreshToken();

        final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.setSecretAccessToken(accessToken.getSecret());
        customAuthenticationSuccessHandler.setSecretRefreshToken(refreshToken.getSecret());
        customAuthenticationSuccessHandler.setExpiresAtAccessToken(accessToken.getExpiresAt());
        customAuthenticationSuccessHandler.setExpiresAtRefreshToken(refreshToken.getExpiresAt());

        setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (!request.getContentType().startsWith(APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication contentType not supported: " + request.getContentType());
        }
        final ObjectMapper mapper = new ObjectMapper();
        try {
            ServletInputStream inputStream = request.getInputStream();
            final UsernamePasswordLoginForm loginForm = mapper.readValue(inputStream, UsernamePasswordLoginForm.class);
            String username = loginForm.getUsername();
            username = (username != null) ? username : "";
            username = username.trim();
            String password = loginForm.getPassword();
            password = (password != null) ? password : "";
            password = password.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            final CommonResult<?> result = CommonResult.fail("Missing request params username or password.");
            try {
                response.setStatus(BAD_REQUEST.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), result);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 认证成功后的处理
     */
    @Data
    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        private String secretAccessToken;
        private String secretRefreshToken;
        private long expiresAtAccessToken;
        private long expiresAtRefreshToken;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            final CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            final List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            final Algorithm algorithmOfAccessToken = Algorithm.HMAC256(getSecretAccessToken());
            final Algorithm algorithmOfRefreshToken = Algorithm.HMAC256(getSecretRefreshToken());

            String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + getExpiresAtAccessToken() * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", roles)
                    .withClaim("uid", user.getUserId())
                    .sign(algorithmOfAccessToken);

            String refreshToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + getExpiresAtRefreshToken() * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", roles)
                    .withClaim("uid", user.getUserId())
                    .sign(algorithmOfRefreshToken);

            response.setStatus(OK.value());
            response.setContentType(APPLICATION_JSON_VALUE);

            final CommonResult<Map<String, String>> result = CommonResult.success(
                    new LinkedHashMap<>() {{
                        put("access_token", accessToken);
                        put("refresh_token", refreshToken);
                    }});
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        }
    }


    /**
     * 认证失败后的处理
     */
    private static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            final ObjectMapper mapper = new ObjectMapper();
            log.error("未授权异常：", exception);
            String message;
            if (exception instanceof BadCredentialsException) {
                message = "用户名或密码错误";
            } else {
                message = exception.getMessage();
            }
            mapper.writeValue(response.getOutputStream(), CommonResult.unauthorized(message, 10));
        }
    }
}
