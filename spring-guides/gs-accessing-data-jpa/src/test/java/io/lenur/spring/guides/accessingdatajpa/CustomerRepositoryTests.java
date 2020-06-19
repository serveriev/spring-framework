package io.lenur.spring.guides.accessingdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

//    @Test
//    public void findByLastId() {
//        entityManager.persist(new Customer("first", "last"));
//
//        Customer customer = repository.findById(1L);
//        assertThat(customer).isNotNull();
//        assertThat(customer.getId()).isEqualTo(1L);
//        assertThat(customer.getFirstName()).isEqualTo("first");
//        assertThat(customer.getLastName()).isEqualTo("last");
//    }
}