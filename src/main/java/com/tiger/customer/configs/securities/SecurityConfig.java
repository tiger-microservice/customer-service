package com.tiger.customer.configs.securities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.customer.constants.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // api post with public endpoints don't need authorization
    private final String[] PUBLIC_ENDPOINTS = {};

    private final CustomJwtDecoder customJwtDecoder;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // setting api method post ko can auth
        httpSecurity.authorizeHttpRequests(request ->
                // permit all public endpoint
                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                // permit all swagger
                .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                .anyRequest()
                .authenticated());

        // setting oauth2 resource server
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint(objectMapper)));

        // HttpSecurity config cors
        httpSecurity.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("*"));
            configuration.setAllowedMethods(List.of("*"));
            configuration.setAllowedHeaders(List.of("*"));
            return configuration;
        }));

        // setting session management
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // todo: off csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(""); // setting default value prefix is empty
        // customer key authorizations
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimDelimiter(AppConstants.SPACE);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AppConstants.JwtKey.CUSTOM_KEY_SCOPE);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

}
