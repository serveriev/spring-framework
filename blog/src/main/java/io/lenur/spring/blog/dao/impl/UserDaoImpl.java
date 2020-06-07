package io.lenur.spring.blog.dao.impl;

import io.lenur.spring.blog.dao.UserDao;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public List<User> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "SELECT u " +
                        "FROM User u " +
                        "LEFT JOIN FETCH u.posts "
                    , User.class);
            return query.getResultList();
        } catch (HibernateException e) {
            String msg = "There is something wrong in getting user's basket";
            throw new DaoException(msg, e);
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String msg = "Can't create a user entity!";
            throw new DaoException(msg, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(User.class, id));
        } catch (HibernateException e) {
            String msg = "Can't fetch a user!";
            throw new DaoException(msg, e);
        }
    }
}
