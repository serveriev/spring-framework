package payroll.runner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import org.assertj.core.api.Assertions;
import payroll.entity.Employee;
import payroll.repository.EmployeeRepository;

@SpringBootTest
class LoadDatabaseTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void initDatabase() {
        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees.size()).isEqualTo(2);
    }
}
