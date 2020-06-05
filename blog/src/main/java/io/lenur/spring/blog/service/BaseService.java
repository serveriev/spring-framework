package io.lenur.spring.blog.service;

public interface BaseService<T> {
    T create(T entity);
}
