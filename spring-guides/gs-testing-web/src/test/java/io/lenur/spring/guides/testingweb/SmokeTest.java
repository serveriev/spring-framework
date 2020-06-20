package io.lenur.spring.guides.testingweb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private HomeController homeController;

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private GreetingController greetingController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(homeController).isNotNull();
        assertThat(greetingService).isNotNull();
        assertThat(greetingController).isNotNull();
    }
}