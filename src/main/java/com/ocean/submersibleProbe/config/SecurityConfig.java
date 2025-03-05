package com.ocean.submersibleProbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Value;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String username = "Luxoft";
    private final String password = "Coutts";
    
 @Bean
 public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
     UserDetails user = User.builder()
             .username(username)
             .password(passwordEncoder.encode(password))
             .roles("USER")
             .build();
     return new InMemoryUserDetailsManager(user);
 }
 
 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
             .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (not recommended for production)
             .authorizeHttpRequests(authz -> authz
                     .anyRequest().authenticated()
             ).httpBasic();
     return http.build();
 }
}
