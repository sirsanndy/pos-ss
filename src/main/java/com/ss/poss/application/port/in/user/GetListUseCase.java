package com.ss.poss.application.port.in.user;

import com.ss.poss.domain.user.model.User;

import java.util.List;

public interface GetListUseCase {
    List<User> getUserList();
}
