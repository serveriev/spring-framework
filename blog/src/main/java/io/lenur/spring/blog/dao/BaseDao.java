package io.lenur.spring.blog.dao;

public interface BaseDao<T> {
    T create(T entity);
}
