package io.lenur.rowmapper.service.impl;

import io.lenur.rowmapper.dao.CityRepository;
import io.lenur.rowmapper.model.City;
import io.lenur.rowmapper.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id);
    }
}
