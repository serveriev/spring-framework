package io.lenur.datasourcebuilder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private ApplicationRunner applicationRunner;

    @Test
    void contextLoads() {
        assertNotNull(applicationRunner);
    }
}
