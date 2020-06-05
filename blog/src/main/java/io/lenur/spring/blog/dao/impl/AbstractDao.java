package io.lenur.spring.blog.dao.impl;

import io.lenur.spring.blog.dao.BaseDao;
import io.lenur.spring.blog.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public T create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String msg = "Can't create an entity!";
            throw new DaoException(msg, e);
        }
    }
}
