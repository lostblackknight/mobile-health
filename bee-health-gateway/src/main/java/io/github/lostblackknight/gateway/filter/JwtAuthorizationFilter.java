package io.github.lostblackknight.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Jwt 授权过滤器
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Order(-999)
@Component
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final List<String> notRequiredAuthorizationUri = List.of(
            "/api/admin/token/**",
            "/api/hospital/token/**",
            "/api/member/token/**",
            "/api/thirdpary/**",
            "/api/search/**",
            "/api/member/sms/sendCode/**",
            "/api/order/payment/notify"
    );

    private final JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String path = request.getURI().getPath();
        // 不需要验证的请求
        for (String uri : notRequiredAuthorizationUri) {
            if (antPathMatcher.match(uri, path)) {
                return chain.filter(exchange);
            }
        }

        final String authorizationHeader = request.getHeaders().getFirst(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            try {
                // 校验
                Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getAccessToken().getSecret());
                final JWTVerifier verifier = JWT.require(algorithm).build();
                final DecodedJWT decodedJWT = verifier.verify(token);
                // 获取信息
                final String username = decodedJWT.getSubject();
                List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                final Long userId = decodedJWT.getClaim("uid").asLong();
                // 封装 TokenInfo
                final ObjectMapper mapper = new ObjectMapper();
                final TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
                tokenInfoDTO.setUid(userId);
                tokenInfoDTO.setPrincipal(username);
                tokenInfoDTO.setRoles(roles);
                String tokenInfo = "";
                try {
                    tokenInfo = mapper.writeValueAsString(tokenInfoDTO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                // 将 TokenInfo 转发到下游服务
                final ServerHttpRequest exchangeRequest = exchange.getRequest();
                final ServerHttpRequest httpRequest = exchangeRequest.mutate().header("token-info", tokenInfo).build();
                return chain.filter(exchange.mutate().request(httpRequest).build());
            } catch (JWTVerificationException e) {
                return unsuccessfulAuthorization(exchange.getResponse(), e);
            }
        } else {
            return unsuccessfulAuthorization(exchange.getResponse(), new RuntimeException("Missing request header AUTHORIZATION."));
        }
    }

    private Mono<Void> unsuccessfulAuthorization(ServerHttpResponse response, Exception e) {
        response.setStatusCode(UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        final CommonResult<?> result = CommonResult.unauthorized("令牌已过期", 11);

        final ObjectMapper mapper = new ObjectMapper();
        String responseResult = "";
        try {
            responseResult = mapper.writeValueAsString(result);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        final DataBuffer dataBuffer = response.bufferFactory().wrap(responseResult.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -300;
    }
}
