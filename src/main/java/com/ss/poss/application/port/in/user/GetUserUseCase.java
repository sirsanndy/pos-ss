package com.ss.poss.application.port.in.user;

import com.ss.poss.domain.user.model.User;

import java.util.UUID;

public interface GetUserUseCase {
    User getUserById(UUID userId);
    User getUserByUsername(String username);
}
