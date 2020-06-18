package io.lenur.spring.guides.managingtransactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void emptyBooks() {
        List<String> bookings = bookRepository.findAllBookings();
        Assert.isTrue(bookings.size() == 0, "The table should be empty");
    }

    @Test
    public void booksFilled() {
        bookRepository.book("Alice", "Bob", "Carol");
        List<String> bookings = bookRepository.findAllBookings();
        Assert.isTrue(bookings.size() == 3, "The table should not be empty");
    }
}
