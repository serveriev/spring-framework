package io.lenur.rowmapper.dao;

import io.lenur.rowmapper.model.City;

import java.util.List;

public interface CityRepository {
    List<City> findAll();
    City findById(Long id);
}
