package io.lenur.spring.guides.messagingjms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.support.converter.MessageConverter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContextLoaderTests {
    @Autowired
    private Receiver receiver;

    @Autowired
    private MessageConverter messageConverter;

    @Test
    void contextLoads() {
        assertThat(receiver).isNotNull();
        assertThat(messageConverter).isNotNull();
    }
}
