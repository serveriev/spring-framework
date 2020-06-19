package io.lenur.spring.guides.caching;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTest {
    private static final String ISBN_1 = "isbn_1";
    private static final String ISBN_2 = "isbn_2";

    @Autowired
    private BookRepository bookRepository;

    @Test
    void whenPassTheSameIsbnGetFromCache() {
        Book book1 = bookRepository.getByIsbn(ISBN_1);
        Book book2 = bookRepository.getByIsbn(ISBN_1);

        assertThat(book1).isEqualTo(book2);
    }

    @Test
    void whenPassDifferentIsbnLoadNotFromCache() {
        Book book1 = bookRepository.getByIsbn(ISBN_1);
        Book book2 = bookRepository.getByIsbn(ISBN_2);

        assertThat(book1).isNotEqualTo(book2);
    }
}
