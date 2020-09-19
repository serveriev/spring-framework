package io.lenur.datajpa.repository;

import io.lenur.datajpa.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    void findAllOrderedByNameDescending() {
        List<City> cities = cityRepository.findAllOrderedByNameDescending();
        assertNotNull(cities);
        assertEquals(8, cities.size());

        City city = cities.get(0);
        assertNotNull(city);
        assertEquals("Warsaw", city.getName());
    }

    @Test
    void findByNameEndsWith() {
        List<City> cities = cityRepository.findByNameEndsWith("lava");
        assertNotNull(cities);
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertNotNull(city);
        assertEquals("Bratislava", city.getName());
    }
}
