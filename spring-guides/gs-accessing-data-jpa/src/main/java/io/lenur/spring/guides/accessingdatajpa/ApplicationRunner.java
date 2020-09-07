package io.lenur.spring.guides.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ApplicationRunner.class);

    private final CustomerRepository repository;

    public ApplicationRunner(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(".... Fetching books");

        // save a few customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        LOGGER.info("Customers found with findAll():");
        LOGGER.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            LOGGER.info(customer.toString());
        }
        LOGGER.info("-------------------------------");

        Optional<Customer> customer = repository.findById(1L);
        if (customer.isPresent()) {
            LOGGER.info("Customer found with findById(1L):");
            LOGGER.info("--------------------------------");
            LOGGER.info(customer.get().toString());
            LOGGER.info("--------------------------------");
        } else {
            LOGGER.error("There is something wrong!");
        }

        LOGGER.info("Customer found with findByLastName('Bauer'):");
        LOGGER.info("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(bauer -> {
            LOGGER.info(bauer.toString());
        });
        LOGGER.info("--------------------------------");
    }
}