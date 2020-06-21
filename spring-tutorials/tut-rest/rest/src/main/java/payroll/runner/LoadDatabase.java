package payroll.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payroll.entity.Employee;
import payroll.repository.EmployeeRepository;

@Configuration
public class LoadDatabase {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            LOGGER.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "USER")));
            LOGGER.info("Preloading " + repository.save(new Employee("Frodo Baggins", "ADMIN")));
        };
    }
}