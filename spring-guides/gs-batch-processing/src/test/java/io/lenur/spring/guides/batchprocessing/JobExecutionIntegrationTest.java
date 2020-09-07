package io.lenur.spring.guides.batchprocessing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
public class JobExecutionIntegrationTest {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobExecutionIntegrationTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    public void run() throws Exception {
        List<Person> persons = jdbcTemplate.query("SELECT first_name, last_name FROM people",
                (rs, row) -> new Person(
                        rs.getString(1),
                        rs.getString(2))
        );

        assertEquals(persons.size(), 5);
    }
}
