package io.lenur.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MailPropertiesTest {
    @Autowired
    private MailProperties properties;

    @Test
    void loadProperties() {
        assertEquals("smtp", properties.getProtocol());
        assertEquals("smtp.gmail.com", properties.getHost());
        assertEquals(465, properties.getPort());
        assertEquals("test", properties.getPassword());
        assertEquals(Collections.singletonList("user1@example.com"), properties.getRecipients());
    }
}
