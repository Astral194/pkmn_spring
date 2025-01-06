package ru.mirea.Pegov.pkmn.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mirea.Pegov.pkmn.security.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService jdbcUserDetailsManager;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                customizer -> customizer
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/cards/**",
                                "/api/v1/students/**",
                                "/api/v1/cards/card-image")
                        .hasAnyRole("ADMIN","USER")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/v1/cards",
                                "/api/v1/students")
                        .hasRole("ADMIN")
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/**").authenticated()
                        .anyRequest().authenticated()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.userDetailsService(jdbcUserDetailsManager);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
