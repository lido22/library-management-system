package com.ra7eeb.assessment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        // Secure specific endpoint
                        .requestMatchers(HttpMethod.POST,"library/users").hasRole("ADMIN")
                        // Allow all other endpoints
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());  // Use Basic auth for simplicity

        return http.build();
    }

    @Bean
    public UserDetailsService adminDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminPass")
                .roles("ADMIN")
                .build();
        UserDetails patron = User.builder()
                .username("patron")
                .password("{noop}patronPass")
                .roles("PATRON")
                .build();

        return new InMemoryUserDetailsManager(admin, patron);
    }

}