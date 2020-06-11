package io.lenur.spring.blog.dao;

import io.lenur.spring.blog.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    List<User> getUsers();

    User update(User user);

    Optional<User> get(Long id);

    Optional<User> findByName(String name);
}
