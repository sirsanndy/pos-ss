package com.ss.poss.domain.user.service;

import com.ss.poss.domain.user.mapper.UserMapper;
import com.ss.poss.infrastructure.adapter.out.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final com.ss.poss.domain.user.model.User user
                = this.repository.findByUsername(username)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user" + username));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}