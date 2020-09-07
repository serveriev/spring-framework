package io.lenur.spring.guides.accessingdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CustomerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void findByLastName() {
        Customer customer = new Customer("first", "last");
        entityManager.persist(customer);

        List<Customer> customers = repository.findByLastName("last");

        assertThat(customers).hasSize(1);
        assertThat(customers).extracting(Customer::getLastName).containsOnly(customer.getLastName());
        assertThat(customers).extracting(Customer::getFirstName).containsOnly(customer.getFirstName());
    }

    @Test
    public void findByLastId() {
        Customer customer = new Customer("first 1", "last 1");
        entityManager.persist(customer);

        Optional<Customer> customerOptional = repository.findById(customer.getId());
        assertThat(customerOptional.isPresent()).isTrue();

        Customer customer1 = customerOptional.get();
        assertThat(customer1.getId()).isEqualTo(customer.getId());
        assertThat(customer1.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(customer1.getLastName()).isEqualTo(customer.getLastName());
    }
}