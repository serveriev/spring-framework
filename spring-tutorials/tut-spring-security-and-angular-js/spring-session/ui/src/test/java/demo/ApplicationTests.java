package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    @LocalServerPort
    private int port;

    private final TestRestTemplate template = new TestRestTemplate();

    private final TestRestTemplate authTemplate = new TestRestTemplate("user", "password");

    @Test
    public void homePageLoads() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"
                + port + "/", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void userEndpointProtected() {
        ResponseEntity<String> response = authTemplate
                .getForEntity("http://localhost:" + port + "/user", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void loginSucceeds() {
        ResponseEntity<String> response = authTemplate
                .getForEntity("http://localhost:" + port + "/user", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void token() {
        TestRestTemplate template = new TestRestTemplate("user", "password");
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/token", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
