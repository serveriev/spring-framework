package io.lenur.rowmapper.repository;

import io.lenur.rowmapper.dao.CityRepository;
import io.lenur.rowmapper.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void sizeOfCitiesShouldBeEqualEight() {
        List<City> cities = cityRepository.findAll();

        assertEquals(8, cities.size());
    }

    @Test
    public void getOneCity() {
        City city = cityRepository.findById(1L);
        assertNotNull(city);
    }
}
