package com.ss.poss.domain.auth.model;

import java.io.Serial;
import java.io.Serializable;

public record AuthRequest (
        String username,
        String password,
        String token,
        String refreshToken
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
