package io.lenur.spring.blog.service.api.impl;

import io.lenur.spring.blog.dao.UserDao;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserDto;
import io.lenur.spring.blog.dto.UserResponseDto;
import io.lenur.spring.blog.exception.ModelNotFoundException;
import io.lenur.spring.blog.mapper.UserMapper;
import io.lenur.spring.blog.service.api.ApiUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUserServiceImpl implements ApiUserService {

    private final UserDao userDao;

    public ApiUserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDto create(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        userDao.create(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userDao
                .getUsers()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto update(Long id, UserDto dto) {
        User user = userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));
        user.setName(dto.getName());

        userDao.update(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDto get(Long id) {
        User user = userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));

        return UserMapper.toResponse(user);
    }
}
