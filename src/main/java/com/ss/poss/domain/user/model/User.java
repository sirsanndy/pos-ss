package com.ss.poss.domain.user.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String refreshToken;
}
