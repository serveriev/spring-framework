package io.lenur.spring.blog.mapper;

import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserResponseDto;

public class UserMapper {
    public static UserResponseDto toResponse(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());

        return dto;
    }
}
