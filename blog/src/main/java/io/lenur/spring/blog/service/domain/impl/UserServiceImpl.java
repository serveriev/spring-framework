package io.lenur.spring.blog.service.domain.impl;

import io.lenur.spring.blog.dao.UserDao;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.exception.ModelNotFoundException;
import io.lenur.spring.blog.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
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
}
