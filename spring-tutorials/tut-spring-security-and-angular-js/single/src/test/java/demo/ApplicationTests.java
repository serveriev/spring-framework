package demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions;

import java.security.Principal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadHomePage() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/", String.class);
        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        Assertions.assertThat("home").isEqualTo(response.getBody());
    }

    @Test
    public void loadResourceWithoutToken() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/resource", String.class);
        Assertions.assertThat(HttpStatus.UNAUTHORIZED).isEqualTo(response.getStatusCode());
    }

    @Test
    public void loadResourceWithToken() {
        ResponseEntity<ResponseDto> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/resource", ResponseDto.class);
        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());

        ResponseDto responseDto = response.getBody();
        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getContent()).isEqualTo("Hello World");
    }

    @Test
    public void userEndpointProtected() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/user", String.class);
        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
    }
}
