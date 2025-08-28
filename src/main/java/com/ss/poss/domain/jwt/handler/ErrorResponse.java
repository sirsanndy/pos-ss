package com.ss.poss.domain.jwt.handler;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String error,
        Instant timestamp,
        String message,
        String path){};
