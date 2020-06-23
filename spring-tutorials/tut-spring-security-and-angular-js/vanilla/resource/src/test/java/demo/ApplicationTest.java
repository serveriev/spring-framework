package demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadResourceWithoutToken() {
        ResponseEntity<Message> response = restTemplate
                .getForEntity("/", Message.class);
        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());

        Message message = response.getBody();
        Assertions.assertThat(message).isNotNull();
        Assertions.assertThat(message.getContent()).isEqualTo("Hello World");
    }
}