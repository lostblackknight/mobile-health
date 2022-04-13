package io.github.lostblackknight.member.config;

import io.github.lostblackknight.member.filter.CustomUsernamePasswordAuthenticationFilter;
import io.github.lostblackknight.member.filter.PhoneCodeAuthenticationFilter;
import io.github.lostblackknight.member.filter.TokenInfoFilter;
import io.github.lostblackknight.member.support.PhoneCodeAuthenticationProvider;
import io.github.lostblackknight.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全相关配置
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JwtProperties jwtProperties;

    private final PhoneCodeAuthenticationProvider phoneCodeAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(phoneCodeAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/token/**", "/sms/sendCode/**").permitAll();
        http.authorizeRequests().antMatchers("/**").hasAnyAuthority("admin", "patient", "doctor");
        http.addFilter(usernamePasswordAuthenticationFilter());
        http.addFilterBefore(phoneCodeAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenInfoFilter(), PhoneCodeAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        return new CustomUsernamePasswordAuthenticationFilter(authenticationManagerBean(), jwtProperties);
    }

    @Bean
    public TokenInfoFilter tokenInfoFilter() {
        return new TokenInfoFilter();
    }

    @Bean
    public PhoneCodeAuthenticationFilter phoneCodeAuthenticationFilter() throws Exception {
        return new PhoneCodeAuthenticationFilter(authenticationManagerBean(), jwtProperties);
    }
}
