package com.ss.poss.domain.user.mapper;

import com.ss.poss.domain.user.model.User;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}
