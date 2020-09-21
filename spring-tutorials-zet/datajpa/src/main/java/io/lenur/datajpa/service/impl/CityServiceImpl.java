package io.lenur.datajpa.service.impl;

import io.lenur.datajpa.entity.City;
import io.lenur.datajpa.repository.CityRepository;
import io.lenur.datajpa.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

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

    @Override
    public List<City> findAllOrderedByNameAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return cityRepository.findAllOrderedByNameAsc(sort);
    }
}
