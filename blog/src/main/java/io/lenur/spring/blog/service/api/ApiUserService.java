package io.lenur.spring.blog.service.api;

import io.lenur.spring.blog.dto.UserDto;
import io.lenur.spring.blog.dto.UserResponseDto;

import java.util.List;

public interface ApiUserService {
    UserResponseDto create(UserDto dto);

    List<UserResponseDto> getUsers();

    UserResponseDto update(Long id, UserDto userDTO);

    UserResponseDto get(Long id);
}
