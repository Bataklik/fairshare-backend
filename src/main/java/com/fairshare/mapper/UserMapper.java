package com.fairshare.mapper;

import com.fairshare.dto.CreateUserDTO;
import com.fairshare.dto.UserDTO;
import com.fairshare.model.User;

public class UserMapper {
    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName()
        );
    }

    public static User toEntity(CreateUserDTO userDto) {
        User userEntity = new User();
        userEntity.setName(userDto.name());
        return userEntity;
    }
}
