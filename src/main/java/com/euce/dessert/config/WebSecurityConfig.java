package com.euce.dessert.config;

import com.euce.dessert.security.jwt.AuthJwtFilter;
import com.euce.dessert.security.jwt.JwtProvider;
import com.euce.dessert.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private final JwtProvider jwtProvider;
    private final AuthJwtFilter jwtFilter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(JwtProvider jwtProvider,
                             AuthJwtFilter jwtFilter,
                             BCryptPasswordEncoder passwordEncoder,
                             UserDetailsServiceImpl userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(jwtProvider.getUri() + "/**").permitAll()
                .antMatchers("/api/v1/products/**").hasAnyAuthority("PRODUCT_VIEW")
                .antMatchers("/api/v1/categories/**").hasAnyAuthority("CATEGORY_VIEW")
                .antMatchers("/api/v1/brands/**").hasAnyAuthority("BRAND_VIEW")
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
