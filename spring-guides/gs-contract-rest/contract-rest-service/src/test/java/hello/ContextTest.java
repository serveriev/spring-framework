package hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContextTest {
    @Autowired
    private PersonController personController;

    @Autowired
    private PersonService personService;

    @Test
    public void contextLoad() {
        Assertions.assertThat(personController).isNotNull();
        Assertions.assertThat(personService).isNotNull();
    }
}
