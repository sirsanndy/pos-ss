package com.ss.poss.domain.jwt.service;

import com.ss.poss.application.port.in.jwt.GetJwtUseCase;
import com.ss.poss.application.port.in.jwt.GetUsernameUseCase;
import com.ss.poss.application.port.in.jwt.ValidateJwtUseCase;
import com.ss.poss.infrastructure.adapter.config.auth.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;


@Service
public class JwtService implements GetJwtUseCase, ValidateJwtUseCase, GetUsernameUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(JwtService.class);

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.cookie-name}")
    private String jwtCookieName;

    public JwtService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String getJwtFromCookie(HttpServletRequest request) {
        LOG.info("CHECK COOKIE FOR JWT {} : {}", jwtCookieName, request.getCookies());
        Cookie cookie = WebUtils.getCookie(request, jwtCookieName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    @Override
    public String getUsername(String token) {
        LOG.info("GET USERNAME FROM TOKEN {}", token);
        return jwtTokenProvider.getUsernameFromToken(token);
    }

    @Override
    public boolean validateToken(String token) {
        LOG.info("VALIDATE TOKEN {}", token);
        return jwtTokenProvider.validateToken(token);
    }
}
