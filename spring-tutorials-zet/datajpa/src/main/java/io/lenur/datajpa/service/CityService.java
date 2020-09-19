package io.lenur.datajpa.service;

import io.lenur.datajpa.entity.City;

import java.util.List;

public interface CityService {
    List<City> findAllOrderedByNameDescending();
}
