package io.lenur.spring.blog.service.impl;

import io.lenur.spring.blog.dao.UserDao;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserDTO;
import io.lenur.spring.blog.dto.UserResponseDTO;
import io.lenur.spring.blog.exception.ModelNotFoundException;
import io.lenur.spring.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDTO create(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        userDao.create(user);

        return new UserResponseDTO(user.getId(), user.getName());
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userDao
                .getUsers()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getName())).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO update(Long id, UserDTO userDTO) {
        User user = userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));
        user.setName(userDTO.getName());

        userDao.update(user);

        return new UserResponseDTO(user.getId(), user.getName());
    }

    @Override
    public UserResponseDTO get(Long id) {
        User user = userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));

        return new UserResponseDTO(user.getId(), user.getName());
    }
}
