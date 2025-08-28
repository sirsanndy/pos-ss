package com.ss.poss.domain.user.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record User (
        UUID userId,
        String username,
        String password,
        String email,
        String phone,
        String refreshToken
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
