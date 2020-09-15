package io.lenur.mail;

import io.lenur.mail.sender.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private Notification notification;

    @Test
    void contextLoads() {
        assertThat(notification).isNotNull();
    }
}
