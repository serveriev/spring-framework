package io.lenur.multimodule;

import io.lenur.multimodule.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private ApplicationService service;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void whenGetMessageHandleGetFromPropertyFromPropertyFile() {
        assertThat(service.message()).isEqualTo("Test application message");
    }
}
