package com.ss.poss.domain.auth.service;

import com.ss.poss.domain.auth.model.AuthRequest;
import com.ss.poss.domain.auth.model.SignupRequest;
import com.ss.poss.domain.user.model.User;
import com.ss.poss.domain.user.service.UserService;
import com.ss.poss.infrastructure.adapter.config.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                       JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        User user = userService.getUserByUsername(authRequest.username());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found");
        }

        user = new User(
                user.userId(),
                user.username(),
                user.password(),
                user.email(),
                user.phone(),
                refreshToken
        );

        userService.saveUser(user);

        return accessToken;
    }

    public String refresh(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            return jwtTokenProvider.generateAccessToken(authentication);
        }

        return "Invalid refresh token";
    }

    public AuthRequest signup(SignupRequest signupRequest) {
        if(Objects.isNull(userService.getUserByUsername(signupRequest.username()))) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(signupRequest.username(), signupRequest.password());
            AuthRequest authRequest = getAuthRequest(signupRequest, authentication);
            User user = new User(
                    null,
                    signupRequest.username(),
                    passwordEncoder.encode(signupRequest.password()),
                    signupRequest.email(),
                    signupRequest.phone(),
                    authRequest.refreshToken()
            );
            userService.saveUser(user);
            return authRequest;
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    private AuthRequest getAuthRequest(SignupRequest signupRequest, Authentication authentication) {
        return new AuthRequest(
                signupRequest.username(),
                passwordEncoder.encode(signupRequest.password()),
                jwtTokenProvider.generateAccessToken(authentication),
                jwtTokenProvider.generateRefreshToken(authentication)
        );
    }
}
