package io.lenur.profile;

import io.lenur.profile.bean.Notificator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private ApplicationRunner applicationRunner;

    @Autowired
    private Notificator notificator;

    @Test
    void contextLoads() {
        assertNotNull(applicationRunner);
        assertNotNull(notificator);
    }
}
