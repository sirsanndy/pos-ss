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
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        User user = userService.getUserByUsername(authRequest.getUsername());
        user.setRefreshToken(refreshToken);
        userService.createUser(user);

        return accessToken;
    }

    public String refresh(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            return jwtTokenProvider.generateAccessToken(authentication);
        } else {
            return "Invalid refresh token";
        }
    }

    public AuthRequest signup(SignupRequest signupRequest) {
        User user = userService.getUserByUsername(signupRequest.getUsername());
        if(Objects.isNull(user)) {
            user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setEmail(signupRequest.getEmail());

            Authentication authentication = new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword());
            AuthRequest authRequest = getAuthRequest(signupRequest, authentication);

            user.setRefreshToken(authRequest.getRefreshToken());

            userService.createUser(user);
            return authRequest;
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    private AuthRequest getAuthRequest(SignupRequest signupRequest, Authentication authentication) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(signupRequest.getUsername());
        authRequest.setRefreshToken(jwtTokenProvider.generateRefreshToken(authentication));
        authRequest.setToken(jwtTokenProvider.generateAccessToken(authentication));
        return authRequest;
    }
}
