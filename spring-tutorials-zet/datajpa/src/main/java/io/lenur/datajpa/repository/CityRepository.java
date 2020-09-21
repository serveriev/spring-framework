package io.lenur.datajpa.repository;

import java.util.List;

import io.lenur.datajpa.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllOrderedByNameDescending();

    @Query("FROM City")
    List<City> findAllOrderedByNameAsc(Sort sort);

    @Query("SELECT c FROM City c WHERE c.name LIKE CONCAT('%',:name)")
    List<City> findByNameEndsWith(@Param(value = "name") String name);

    @Query("SELECT c FROM City c WHERE c.name LIKE CONCAT('%',:name) AND c.population < :num")
    List<City> findByNameEndingWithAndPopulationLessThan(@Param("name") String name,
                                                         @Param("num") Integer num);
}