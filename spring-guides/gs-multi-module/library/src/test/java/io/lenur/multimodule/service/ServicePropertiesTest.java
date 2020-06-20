package io.lenur.multimodule.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest("service.message=Test service message")
class ServicePropertiesTest {
    @Autowired
    private ServiceProperties serviceProperties;

    @Test
    void contextLoad() {
        assertThat(serviceProperties).isNotNull();
    }

    @Test
    void whenGetMessageHandleGetFromPropertyFromAnnotation() {
        assertThat(serviceProperties.getMessage()).isEqualTo("Test service message");
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
