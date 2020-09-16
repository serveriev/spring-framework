package io.lenur.conditional;

import io.lenur.conditional.service.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeanLoadTest {
    @Autowired
    private Message message;

    @Test
    public void contextLoads() {
        assertNotNull(message);
    }

    @Test
    public void loadHelloMessageBean() {
        assertEquals("Hello", message.getMessage());
    }
}
