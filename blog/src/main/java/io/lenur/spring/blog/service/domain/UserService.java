package io.lenur.spring.blog.service.domain;

import io.lenur.spring.blog.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    List<User> getUsers();

    User update(Long id, User user);

    User get(Long id);

    Optional<User> getByName(String name);
}
