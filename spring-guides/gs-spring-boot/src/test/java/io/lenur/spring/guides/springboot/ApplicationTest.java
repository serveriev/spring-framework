package io.lenur.spring.guides.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private HelloController controller;

    @Autowired
    private ShowDeclaredBeans declaredBeans;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(declaredBeans).isNotNull();
    }
}
