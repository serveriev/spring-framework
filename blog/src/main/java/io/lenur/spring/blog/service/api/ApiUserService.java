package io.lenur.spring.blog.service.api;

import io.lenur.spring.blog.dto.UserCreateDto;
import io.lenur.spring.blog.dto.UserResponseDto;
import io.lenur.spring.blog.dto.UserUpdateDto;

import java.util.List;

public interface ApiUserService {
    UserResponseDto create(UserCreateDto dto);

    List<UserResponseDto> getUsers();

    UserResponseDto update(Long id, UserUpdateDto userDTO);

    UserResponseDto get(Long id);
}
