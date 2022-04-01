package io.github.lostblackknight.admin.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
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
                template.header("Authorization", authorization);
                template.header("Cookie", cookie);
            }
        };
    }
}