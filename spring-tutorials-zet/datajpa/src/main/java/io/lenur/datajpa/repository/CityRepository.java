package io.lenur.datajpa.repository;

import java.util.List;

import io.lenur.datajpa.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllOrderedByNameDescending();

    @Query("SELECT c FROM City c WHERE c.name LIKE %:name")
    List<City> findByNameEndsWith(@Param(value = "name") String name);
}