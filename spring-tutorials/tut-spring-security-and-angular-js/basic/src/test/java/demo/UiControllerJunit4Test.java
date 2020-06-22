package demo;

import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UiControllerJunit4Test {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void openHomeWithoutToken() {
        ResponseEntity<String> response = restTemplate
            .getForEntity("http://localhost:" + port + "/", String.class);
        Assertions.assertThat(HttpStatus.UNAUTHORIZED).isEqualTo(response.getStatusCode());
    }

    @Test
    public void openHomeWithToken() {
        ResponseEntity<String> response = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity("http://localhost:" + port + "/", String.class);

        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        Assertions.assertThat("home").isEqualTo(response.getBody());
    }

    @Test
    public void viewProtectedPage() {
        ResponseEntity<TestResponse> response = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity("http://localhost:" + port + "/resource", TestResponse.class);

        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());

        TestResponse testResponse = response.getBody();
        Assertions.assertThat(testResponse).isNotNull();
        Assertions.assertThat(testResponse.getContent()).isEqualTo("Hello World");
    }
}
