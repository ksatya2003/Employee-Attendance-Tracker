package com.example.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/attendance/mark").hasRole("EMPLOYEE")
                .requestMatchers("/api/attendance/**").hasRole("MANAGER")
                .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails employee = User.withUsername("employee")
            .password(passwordEncoder().encode("pass"))
            .roles("EMPLOYEE")
            .build();

        UserDetails manager = User.withUsername("manager")
            .password(passwordEncoder().encode("pass"))
            .roles("MANAGER")
            .build();

        return new InMemoryUserDetailsManager(employee, manager);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
