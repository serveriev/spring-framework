package io.lenur.spring.guides.accessingdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccessingDataJpaApplicationTests {
    @Autowired
    private CustomerRepository repository;

    @Test
    void contextLoads() {
        assertThat(repository).isNotNull();
    }
}
