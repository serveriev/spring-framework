package io.lenur.datajpa.service.impl;

import io.lenur.datajpa.entity.City;
import io.lenur.datajpa.repository.CityRepository;
import io.lenur.datajpa.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAllOrderedByNameDescending() {
        return this.cityRepository.findAllOrderedByNameDescending();
    }
}
