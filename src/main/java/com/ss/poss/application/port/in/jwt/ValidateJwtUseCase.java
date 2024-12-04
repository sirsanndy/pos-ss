package com.ss.poss.application.port.in.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface ValidateJwtUseCase {
    boolean validateToken(String token, UserDetails userDetails);
}
