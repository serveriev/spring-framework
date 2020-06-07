package io.lenur.spring.blog.service;

import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserDTO;
import io.lenur.spring.blog.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserDTO userDTO);

    List<UserResponseDTO> getUsers();

    UserResponseDTO update(Long id, UserDTO userDTO);

    UserResponseDTO get(Long id);
}
