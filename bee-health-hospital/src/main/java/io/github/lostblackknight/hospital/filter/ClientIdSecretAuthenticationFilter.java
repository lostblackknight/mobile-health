package io.github.lostblackknight.hospital.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.hospital.support.ClientIdSecretAuthenticationToken;
import io.github.lostblackknight.hospital.vo.ClientIdSecretLoginForm;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.properties.JwtProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public class ClientIdSecretAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/token", "POST");

    public ClientIdSecretAuthenticationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        super(ANT_PATH_REQUEST_MATCHER, authenticationManager);
        final JwtProperties.AccessToken accessToken = jwtProperties.getAccessToken();

        final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.setSecretAccessToken(accessToken.getSecret());
        customAuthenticationSuccessHandler.setExpiresAtAccessToken(accessToken.getExpiresAt());
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
            final ClientIdSecretLoginForm loginForm = mapper.readValue(inputStream, ClientIdSecretLoginForm.class);
            String clientId = loginForm.getClientId();
            clientId = (clientId != null) ? clientId : "";
            clientId = clientId.trim();
            String clientSecret = loginForm.getClientSecret();
            clientSecret = (clientSecret != null) ? clientSecret : "";
            clientSecret = clientSecret.trim();
            ClientIdSecretAuthenticationToken authRequest = new ClientIdSecretAuthenticationToken(clientId, clientSecret);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            final CommonResult<?> result = CommonResult.fail("Missing request params clientId or clientSecret.");
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

    @Data
    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        private String secretAccessToken;
        private long expiresAtAccessToken;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            final HospitalClientDetail hospitalClientDetail = (HospitalClientDetail) authentication.getPrincipal();
            final List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            final Algorithm algorithmOfAccessToken = Algorithm.HMAC256(getSecretAccessToken());

            String accessToken = JWT.create()
                    .withSubject(hospitalClientDetail.getHospitalId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + getExpiresAtAccessToken() * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", roles)
                    .withClaim("uid", hospitalClientDetail.getId())
                    .sign(algorithmOfAccessToken);

            response.setStatus(OK.value());
            response.setContentType(APPLICATION_JSON_VALUE);

            final CommonResult<Map<String, String>> result = CommonResult.success(
                    new LinkedHashMap<>() {{
                        put("access_token", accessToken);
                    }});
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        }
    }


    /**
     * 认证失败后的处理
     */
    @Slf4j
    private static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            final ObjectMapper mapper = new ObjectMapper();
            log.error("未授权异常：", exception);
            String message;
            if (exception instanceof BadCredentialsException) {
                message = "客户端ID或客户端Secret错误";
            } else {
                message = exception.getMessage();
            }
            mapper.writeValue(response.getOutputStream(), CommonResult.unauthorized(message, 10));
        }
    }

    protected void setDetails(HttpServletRequest request, ClientIdSecretAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
