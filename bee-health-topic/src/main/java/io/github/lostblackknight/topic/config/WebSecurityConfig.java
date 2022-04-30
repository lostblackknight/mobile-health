package io.github.lostblackknight.topic.config;

import io.github.lostblackknight.topic.filter.TokenInfoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全相关配置
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/category/list").permitAll();
        http.authorizeRequests().antMatchers("/**").hasAnyAuthority("admin", "doctor", "patient");
        http.addFilterBefore(tokenInfoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public TokenInfoFilter tokenInfoFilter() {
        return new TokenInfoFilter();
    }
}
