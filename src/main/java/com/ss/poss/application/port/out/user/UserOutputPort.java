package com.ss.poss.application.port.out.user;

import com.ss.poss.domain.user.model.User;

import java.util.List;
import java.util.UUID;

public interface UserOutputPort {
    User getUserById(UUID userId);
    User saveUser(User user);
    List<User> getAllUsers();
}
