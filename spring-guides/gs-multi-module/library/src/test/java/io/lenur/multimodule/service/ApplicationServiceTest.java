package io.lenur.multimodule.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest("service.message=Test service message")
class ApplicationServiceTest {
    @Autowired
    private ApplicationService service;

    @Test
    void contextLoad() {
        assertThat(service).isNotNull();
    }

    @Test
    void whenGetMessageHandleGetFromPropertyFromAnnotation() {
        assertThat(service.message()).isEqualTo("Test service message");
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
