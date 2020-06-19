package io.lenur.spring.guides.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class BookRepositoryImpl implements BookRepository {
    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        return new Book(atomicLong.incrementAndGet(), isbn, "Some book");
    }
}