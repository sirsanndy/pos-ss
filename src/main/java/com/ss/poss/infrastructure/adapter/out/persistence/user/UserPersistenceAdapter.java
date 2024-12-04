package com.ss.poss.infrastructure.adapter.out.persistence.user;

import com.ss.poss.application.port.out.user.UserOutputPort;
import com.ss.poss.domain.user.mapper.UserMapper;
import com.ss.poss.domain.user.model.User;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Adapter
public class UserPersistenceAdapter implements UserOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(UserPersistenceAdapter.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(UUID userId) {
        LOG.info("Get user by id: {} to DB layer", userId);
        return userRepository.findById(userId)
                .map(userMapper::toUser)
                .orElse(null);
    }

    @Override
    public User saveUser(User user) {
        LOG.info("Save user: {} to DB layer", user);
        UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.save(userEntity);
        user.setId(userEntity.getUserId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        LOG.info("Get all users to DB layer");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUser)
                .toList();
    }

    @Override
    public User getUserByUsername(String username) {
        LOG.info("Get user by username: {} to DB layer", username);
        return userRepository.findByUsername(username)
                .map(userMapper::toUser)
                .orElse(null);
    }
}
