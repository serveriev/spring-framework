package io.lenur.spring.blog.service.api.impl;

import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.domain.RoleName;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserCreateDto;
import io.lenur.spring.blog.dto.UserResponseDto;
import io.lenur.spring.blog.dto.UserUpdateDto;
import io.lenur.spring.blog.mapper.UserMapper;
import io.lenur.spring.blog.service.api.ApiUserService;
import io.lenur.spring.blog.service.domain.RoleService;
import io.lenur.spring.blog.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUserServiceImpl implements ApiUserService {
    private final UserService userService;
    private final RoleService roleService;

    public ApiUserServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());

        for (RoleName roleName : dto.getRoles()) {
            Role role = roleService.getByName(roleName);
            user.addRole(role);
        }

        userService.create(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userService
                .getUsers()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto update(Long id, UserUpdateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user = userService.update(id, user);

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDto get(Long id) {
        User user = userService.get(id);

        return UserMapper.toResponse(user);
    }
}
