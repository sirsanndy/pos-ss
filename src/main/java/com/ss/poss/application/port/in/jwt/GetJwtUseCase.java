package com.ss.poss.application.port.in.jwt;

import jakarta.servlet.http.HttpServletRequest;

public interface GetJwtUseCase {
    String getJwtFromCookie(HttpServletRequest request);
}
