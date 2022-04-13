package io.github.lostblackknight.member.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
@Slf4j
public class FeignConfig {
    /**
     * 解决feign远程调用请求头丢失问题，通过拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {
            // 1、使用 RequestContextHolder 拿到刚进来的请求数据
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (requestAttributes != null) {
                // 老请求
                HttpServletRequest request = requestAttributes.getRequest();
                //2、同步请求头的数据
                String authorization = request.getHeader("Authorization");
                String cookie = request.getHeader("Cookie");
                final String tokenInfo = request.getHeader("token-info");
                template.header("Authorization", authorization);
                template.header("Cookie", cookie);
                template.header("token-info", tokenInfo);
                log.info("当前用户的token-info：{}", tokenInfo);
            }
        };
    }
}