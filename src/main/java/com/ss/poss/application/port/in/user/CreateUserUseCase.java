package com.ss.poss.application.port.in.user;

import com.ss.poss.domain.user.model.User;

public interface CreateUserUseCase {
    User saveUser(User user);
}
