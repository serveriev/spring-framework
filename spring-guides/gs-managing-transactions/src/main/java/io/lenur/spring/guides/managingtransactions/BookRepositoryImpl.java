package io.lenur.spring.guides.managingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(BookRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public void book(String... persons) {
        String query = "INSERT INTO BOOKINGS (FIRST_NAME) VALUES (?)";
        List<Object[]> parameters = new ArrayList<Object[]>();

        for (String person : persons) {
            LOGGER.info("Booking " + person + " in a seat...");
            parameters.add(new Object[] {person});
        }
        jdbcTemplate.batchUpdate(query, parameters);
    }

    @Override
    public List<String> findAllBookings() {
        return jdbcTemplate.query("SELECT FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }
}
