package io.lenur.spring.guides.relational.data.access.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

@SpringBootTest
public class CreateDatabaseCommandTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void tableExists() {
        String query = "SELECT EXISTS(SELECT * FROM customers)";
        boolean exists = jdbcTemplate.queryForObject(query, new Object[]{}, Boolean.class);
        Assert.isTrue(exists);
    }
}
