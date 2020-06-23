package io.lenur.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;

@SpringBootTest
public class DatabaseLoaderTest {
    @Autowired
    private DatabaseLoader databaseLoader;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void run() throws Exception {
        databaseLoader.run();
        long count = employeeRepository.count();
        Assertions.assertThat(count).isGreaterThan(0);
    }
}
