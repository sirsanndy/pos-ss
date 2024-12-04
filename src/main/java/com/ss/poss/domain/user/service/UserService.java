package com.ss.poss.domain.user.service;

import com.ss.poss.application.port.in.user.CreateUserUseCase;
import com.ss.poss.application.port.in.user.GetListUseCase;
import com.ss.poss.application.port.in.user.GetUserUseCase;
import com.ss.poss.domain.user.model.User;
import com.ss.poss.infrastructure.adapter.out.persistence.user.UserPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements CreateUserUseCase, GetUserUseCase, GetListUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserPersistenceAdapter userPersistenceAdapter;

    public UserService(UserPersistenceAdapter userPersistenceAdapter) {
        this.userPersistenceAdapter = userPersistenceAdapter;
    }

    @Override
    public User createUser(User user) {
        LOG.info("Create user: {} service", user);
        return userPersistenceAdapter.saveUser(user);
    }

    @Override
    public List<User> getUserList() {
        LOG.info("Get user list service");
        return userPersistenceAdapter.getAllUsers();
    }

    @Override
    public User getUserById(UUID userId) {
        LOG.info("Get user by id: {} service", userId);
        return userPersistenceAdapter.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        LOG.info("Get user by name: {} service", username);
        return userPersistenceAdapter.getUserByUsername(username);
    }
}
