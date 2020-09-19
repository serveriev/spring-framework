package io.lenur.datasourcebuilder.runner;

import io.lenur.datasourcebuilder.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LogManager
            .getLogger(ApplicationRunner.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        LOGGER.info("[CREATING books table]");
        jdbcTemplate.execute("DROP TABLE IF EXISTS books");
        jdbcTemplate.execute("CREATE TABLE books(id SERIAL, name VARCHAR(255), price DECIMAL(5, 2))");

        LOGGER.info("[SAVING]");
        List<Book> books = Arrays.asList(
                new Book("Effective Java", new BigDecimal("10.00")),
                new Book("Java - The Complete Reference", new BigDecimal("20.99")),
                new Book("Head First Java", new BigDecimal("43.3")),
                new Book("Java Concurrency in Practice", new BigDecimal("13.19"))
        );
        books.forEach(book -> {
            LOGGER.info("Saving...{}", book);
            jdbcTemplate.update(
                    "INSERT INTO books (name, price) values(?, ?)",
                    book.getName(), book.getPrice());
        });

        LOGGER.info("SELECTING ...");
        String query = "SELECT * FROM books";
        List<Book> booksPersist = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Book.class));

        for (Book book: booksPersist) {
            LOGGER.info("{}", book);
        }
    }
}