package com.ss.poss.infrastructure.adapter.config;

import com.ss.poss.domain.user.service.UserDetailsServiceImpl;
import com.ss.poss.infrastructure.adapter.config.auth.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final Http401UnauthorizedEntryPoint unauthorizedEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                 AuthenticationProvider authenticationProvider, Http401UnauthorizedEntryPoint unauthorizedEntryPoint,
                                 CustomAccessDeniedHandler accessDeniedHandler, UserDetailsServiceImpl userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//                                .permitAll()
                                .requestMatchers(
                                        "api/auth/**",
                                        "v3/api-docs",
                                        "v3/api-docs/**",
                                        "swagger-resources",
                                        "swagger-resources/**",
                                        "configuration/ui",
                                        "configuration/security",
                                        "swagger-ui/**",
                                        "webjars/**",
                                        "swagger-ui.html",
                                        "login/**",
                                        "signup/**",
                                        "login",
                                        "signup",
                                        "static/**",
                                        "favicon.ico",
                                        "index.html",
                                        "assets/**",
                                        "/error")
                                .permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}