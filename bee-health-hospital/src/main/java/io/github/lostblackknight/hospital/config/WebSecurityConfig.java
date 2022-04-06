package io.github.lostblackknight.hospital.config;

import io.github.lostblackknight.hospital.filter.ClientIdSecretAuthenticationFilter;
import io.github.lostblackknight.hospital.filter.TokenInfoFilter;
import io.github.lostblackknight.hospital.support.ClientIdSecretAuthenticationProvider;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    private final JwtProperties jwtProperties;

    private final ClientIdSecretAuthenticationProvider clientIdSecretAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(clientIdSecretAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/token/**").permitAll();
        http.authorizeRequests().antMatchers("/**").hasAnyAuthority("admin", "hospital");
        http.addFilterBefore(clientIdSecretAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenInfoFilter(), ClientIdSecretAuthenticationFilter.class);
    }

    @Bean
    public TokenInfoFilter tokenInfoFilter() {
        return new TokenInfoFilter();
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
    public ClientIdSecretAuthenticationFilter clientIdSecretAuthenticationFilter() throws Exception {
        return new ClientIdSecretAuthenticationFilter(authenticationManagerBean(), jwtProperties);
    }
}
