package hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.assertj.core.api.Assertions;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    public void findPersonById() {
        Person person = personService.findPersonById(1L);

        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getId()).isEqualTo(1L);
        Assertions.assertThat(person.getName()).isEqualTo("Richard");
        Assertions.assertThat(person.getSurname()).isEqualTo("Gere");
    }
}
