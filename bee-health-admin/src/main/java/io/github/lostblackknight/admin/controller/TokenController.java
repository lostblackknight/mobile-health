package io.github.lostblackknight.admin.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.admin.service.HospitalClientDetailService;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.service.UserService;
import io.github.lostblackknight.model.entity.admin.HospitalClientDetail;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtProperties jwtProperties;

    private final UserService userService;

    private final HospitalClientDetailService hospitalClientDetailService;

    private final RoleService roleService;

    @GetMapping("/token/refresh")
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());

            try {
                final Algorithm algorithmOfAccessToken = Algorithm.HMAC256(jwtProperties.getAccessToken().getSecret());
                final Algorithm algorithmOfRefreshToken = Algorithm.HMAC256(jwtProperties.getRefreshToken().getSecret());
                // 校验
                final JWTVerifier verifier = JWT.require(algorithmOfRefreshToken).build();
                final DecodedJWT decodedJWT = verifier.verify(refreshToken);
                final Long userId = decodedJWT.getClaim("uid").asLong();

                final User user = userService.getUserById(userId);
                if (user == null) {
                    throw new UsernameNotFoundException("username not fond in database.");
                }

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (jwtProperties.getAccessToken().getExpiresAt() * 60 * 1000)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getTag).collect(Collectors.toList()))
                        .withClaim("uid", user.getId())
                        .sign(algorithmOfAccessToken);

                refreshSuccessHandle(response, accessToken, refreshToken);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                refreshFailHandle(response);
            }
        } else {
            refreshFailHandle(response);
        }
    }

    private void refreshSuccessHandle(HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(OK.value());
        // 获取刷新令牌成功
        final CommonResult<Map<String, String>> result = CommonResult.success(
                new LinkedHashMap<>() {{
                    put("access_token", accessToken);
                    put("refresh_token", refreshToken);
                }}, 13, "令牌刷新成功");
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    private void refreshFailHandle(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        final CommonResult<?> result = CommonResult.unauthorized("令牌刷新失败", 12);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    @GetMapping("/token/hospital/refresh")
    public void getHospitalRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());

            try {
                final Algorithm algorithmOfAccessToken = Algorithm.HMAC256(jwtProperties.getAccessToken().getSecret());
                final Algorithm algorithmOfRefreshToken = Algorithm.HMAC256(jwtProperties.getRefreshToken().getSecret());
                // 校验
                final JWTVerifier verifier = JWT.require(algorithmOfRefreshToken).build();
                final DecodedJWT decodedJWT = verifier.verify(refreshToken);
                final Long hospitalClientDetailId = decodedJWT.getClaim("uid").asLong();

                final HospitalClientDetail hospitalClientDetail = hospitalClientDetailService.getById(hospitalClientDetailId);
                if (hospitalClientDetail == null) {
                    throw new UsernameNotFoundException("hospitalClientDetail not fond in database.");
                }

                final List<Role> roles = roleService.getRolesByHospitalClientDetailId(hospitalClientDetailId);

                String accessToken = JWT.create()
                        .withSubject(hospitalClientDetail.getHospitalId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (jwtProperties.getAccessToken().getExpiresAt() * 60 * 1000)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", roles.stream().map(Role::getTag).collect(Collectors.toList()))
                        .withClaim("uid", hospitalClientDetail.getId())
                        .sign(algorithmOfAccessToken);

                refreshSuccessHandle(response, accessToken, refreshToken);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                refreshFailHandle(response);
            }
        } else {
            refreshFailHandle(response);
        }
    }
}
