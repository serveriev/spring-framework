package io.lenur.spring.blog.service.domain;

import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.dto.UserDto;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getUsers();

    User update(Long id, User user);

    User get(Long id);
}
