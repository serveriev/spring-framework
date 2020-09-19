package io.lenur.datajpa.repository;

import java.util.List;

import io.lenur.datajpa.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllOrderedByNameDescending();
}