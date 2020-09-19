package io.lenur.rowmapper;

import io.lenur.rowmapper.dao.CityRepository;
import io.lenur.rowmapper.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void loadBeans() {
        assertNotNull(cityService);
        assertNotNull(cityRepository);
    }
}
