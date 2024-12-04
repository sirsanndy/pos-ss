package com.ss.poss.application.port.in.jwt;

public interface ValidateJwtUseCase {
    boolean validateToken(String token);
}
