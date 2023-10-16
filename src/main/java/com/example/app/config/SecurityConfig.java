package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/", "/register", "/login").permitAll()
                .antMatchers("/admin", "admin/**").hasRole("ADMIN")
                .antMatchers("favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    if (authentication.getAuthorities().stream()
                            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                        response.sendRedirect("/admin");
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                        response.sendRedirect("/user");
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }

}


