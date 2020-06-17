package io.lenur.spring.guides.consuming.rest;

import io.lenur.spring.guides.consuming.rest.task.QuoterFetcherTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConsumingRestApplicationTests {
    @Autowired
    private QuoterFetcherTask fetcherTask;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(fetcherTask).isNotNull();
        assertThat(restTemplate).isNotNull();
    }
}
