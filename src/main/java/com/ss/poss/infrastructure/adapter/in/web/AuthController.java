package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.auth.model.AuthRequest;
import com.ss.poss.domain.auth.model.SignupRequest;
import com.ss.poss.domain.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        LOG.info("Authenticating {} request", authRequest);
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/refresh")
    public String refresh(@RequestParam String token) {
        LOG.info("Refreshing token {} request", token);
        return authService.refresh(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        LOG.info("Signing up {} request", signupRequest);
        return ResponseEntity.ok(authService.signup(signupRequest));
    }
}
