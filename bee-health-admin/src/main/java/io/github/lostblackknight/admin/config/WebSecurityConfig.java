package io.github.lostblackknight.admin.config;

import io.github.lostblackknight.admin.filter.CustomUsernamePasswordAuthenticationFilter;
import io.github.lostblackknight.admin.filter.TokenInfoFilter;
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/token/**").permitAll();
        http.authorizeRequests().antMatchers("/roles/tag/**", "/roles/batch/**").hasAnyAuthority("admin", "service");
        http.authorizeRequests().antMatchers("/dict/**").hasAnyAuthority("admin", "service", "hospital");
        http.authorizeRequests().antMatchers("/**").hasAuthority("admin");
        http.addFilter(usernamePasswordAuthenticationFilter());
        http.addFilterBefore(tokenInfoFilter(), CustomUsernamePasswordAuthenticationFilter.class);
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
}
