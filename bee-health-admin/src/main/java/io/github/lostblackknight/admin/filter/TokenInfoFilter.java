package io.github.lostblackknight.admin.filter;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.admin.support.TokenInfoContextHolder;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Token 信息过滤器
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public class TokenInfoFilter extends OncePerRequestFilter {
    /**
     * 不需要授权的请求
     */
    private static final List<AntPathRequestMatcher> ANT_PATH_REQUEST_MATCHERS = new ArrayList<>(Arrays.asList(
            new AntPathRequestMatcher("/token/**")
    ));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (notRequiresAuthorization(request)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authenticationResult = attemptAuthorization(request);
        if (authenticationResult == null) {
            chain.doFilter(request, response);
            return;
        }
        successfulAuthorization(request, response, chain, authenticationResult);
    }

    /**
     * 授权
     *
     * @param request request
     * @return 授权成功返回 Authentication 对象，不成功返回 null
     */
    private Authentication attemptAuthorization(HttpServletRequest request) throws JsonProcessingException {
        String tokenInfo = request.getHeader("token-info");
        final ObjectMapper mapper = new ObjectMapper();
        final TokenInfoDTO tokenInfoDTO = mapper.readValue(tokenInfo, TokenInfoDTO.class);
        final List<String> roles = tokenInfoDTO.getRoles();
        // 设置 token 信息
        this.logger.info("添加 token 上下文。");
        TokenInfoContextHolder.set(tokenInfoDTO);
        if (CollUtil.isNotEmpty(roles)) {
            Collection<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(tokenInfoDTO.getPrincipal(), null, authorities);
        }
        return null;
    }

    private void successfulAuthorization(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authResult);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s.", authResult));
        }
        try {
            chain.doFilter(request, response);
        } finally {
            // 清除 token 信息
            this.logger.info("清除 token 上下文。");
            TokenInfoContextHolder.remove();
        }
    }

    private boolean notRequiresAuthorization(HttpServletRequest request) {
        for (AntPathRequestMatcher antPathRequestMatcher : ANT_PATH_REQUEST_MATCHERS) {
            if (antPathRequestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
