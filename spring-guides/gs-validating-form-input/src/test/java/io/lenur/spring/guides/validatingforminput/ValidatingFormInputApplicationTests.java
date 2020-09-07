package io.lenur.spring.guides.validatingforminput;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidatingFormInputApplicationTests {
    @Autowired
    private WebController webController;

    @Test
    void contextLoads() {
        assertThat(webController).isNotNull();
    }
}
