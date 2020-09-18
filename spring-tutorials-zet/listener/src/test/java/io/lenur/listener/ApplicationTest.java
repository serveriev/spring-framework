package io.lenur.listener;

import io.lenur.listener.listener.AppListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private AppListener appListener;

    @Test
    void contextLoads() {
        assertNotNull(appListener);
    }
}
