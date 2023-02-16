package com.kieutrilang.blogwebsite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeHttpRequests(authz -> {
                    // USER
                    authz.requestMatchers("/api/users").hasRole("USER");
                    authz.requestMatchers("/api/posts").hasRole("USER");
                    // ADMIN

                    // ANONYMOUS
                    authz.anyRequest().permitAll();
                })
                .httpBasic();
        return http.build();
    }
}
