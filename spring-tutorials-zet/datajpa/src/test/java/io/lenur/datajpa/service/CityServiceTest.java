package io.lenur.datajpa.service;

import io.lenur.datajpa.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @Test
    void findAllOrderedByNameDescending() {
        List<City> cities = cityService.findAllOrderedByNameDescending();
        assertNotNull(cities);
        assertEquals(8, cities.size());

        City city = cities.get(0);
        assertNotNull(city);
        assertEquals("Warsaw", city.getName());
    }
}
