package com.example.demo.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.example.demo.user.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));  // Allow all origins
        configuration.setAllowedMethods(Arrays.asList("*"));  // Allow all HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("*"));  // Allow all headers
        source.registerCorsConfiguration("/**", configuration);  // Apply CORS configuration

        http
                .authorizeHttpRequests(authorize ->
                        authorize
                               
                                .anyRequest().permitAll()  // Allow all other requests
                )
            .cors(cors -> cors.configurationSource(source))  // Use the configured source
            .csrf(AbstractHttpConfigurer::disable);  // Disable CSRF

        return http.build();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        return new JwtAuthenticationFilter(jwtService, customUserDetailsService);
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        // You can set userDetailsService or authentication provider here
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService())
            .passwordEncoder(passwordEncoder()); // Use your custom password encoder
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        // Return your CustomUserDetailsService implementation here
        return new CustomUserDetailsService();
    }
}



