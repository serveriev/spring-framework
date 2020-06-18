package io.lenur.spring.guides.managingtransactions;

import java.util.List;

public interface BookRepository {
    void book(String... persons);

    List<String> findAllBookings();
}
