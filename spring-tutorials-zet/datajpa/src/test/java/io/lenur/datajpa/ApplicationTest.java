package io.lenur.datajpa;

import io.lenur.datajpa.repository.CityRepository;
import io.lenur.datajpa.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Test
    void contextLoads() {
        assertNotNull(cityRepository);
        assertNotNull(cityService);
    }
}
