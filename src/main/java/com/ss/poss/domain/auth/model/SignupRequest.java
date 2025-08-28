package com.ss.poss.domain.auth.model;

import java.io.Serial;
import java.io.Serializable;

public record SignupRequest (
        String username,
        String password,
        String email,
        String phone
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4613034645623965348L;
}
