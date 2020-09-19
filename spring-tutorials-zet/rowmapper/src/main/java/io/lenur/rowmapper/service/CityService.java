package io.lenur.rowmapper.service;

import io.lenur.rowmapper.model.City;

import java.util.List;

public interface CityService {
    List<City> findAll();
    City findById(Long id);
}
