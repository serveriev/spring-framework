package payroll.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payroll.entity.Employee;
import payroll.entity.Order;
import payroll.entity.Status;
import payroll.repository.EmployeeRepository;
import payroll.repository.OrderRepository;

@Configuration
public class LoadDatabase {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            employeeRepository.save(new Employee("Bilbo", "burglar"));
            employeeRepository.save(new Employee("Frodo", "thief"));

            employeeRepository.findAll().forEach(employee -> LOGGER.info("Preloaded " + employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                LOGGER.info("Preloaded " + order);
            });
        };
    }
}
