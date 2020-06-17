package io.lenur.spring.guides.relational.data.access;

import io.lenur.spring.guides.relational.data.access.command.CreateDatabaseCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RelationalDataAccessApplicationTests {
    @Autowired
    private CreateDatabaseCommand command;

    @Test
    void contextLoads() {
        assertThat(command).isNotNull();
    }
}
