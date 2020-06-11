package io.lenur.spring.blog.service.domain.impl;

import io.lenur.spring.blog.dao.UserDao;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.exception.ModelNotFoundException;
import io.lenur.spring.blog.service.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        String password = user.getPassword();
        String passwordEncoded = passwordEncoder.encode(password);
        user.setPassword(passwordEncoded);

        return userDao.create(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User update(Long id, User user) {
        User userPersist = userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));
        userPersist.setName(user.getName());

        return userDao.update(userPersist);
    }

    @Override
    public User get(Long id) {
        return userDao
                .get(id)
                .orElseThrow(() -> new ModelNotFoundException("An user doesn't exist"));
    }

    @Override
    public Optional<User> getByName(String name) {
        return userDao.findByName(name);
    }
}
