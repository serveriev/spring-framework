package io.lenur.spring.guides.managingtransactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ManagingTransactionsApplicationTests {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
        assertThat(bookRepository).isNotNull();
    }
}
