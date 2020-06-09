package io.lenur.spring.blog.service.domain;

public interface BaseService<T> {
    T create(T entity);
}
