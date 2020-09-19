package io.lenur.rowmapper.dao.impl;

import io.lenur.rowmapper.dao.CityRepository;
import io.lenur.rowmapper.mapper.CityMapper;
import io.lenur.rowmapper.model.City;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {
    private final JdbcTemplate jdbcTemplate;

    public CityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<City> findAll() {
        String query = "SELECT * FROM city";

        return jdbcTemplate.query(query, new CityMapper());
    }

    @Override
    public City findById(Long id) {
        String sql = "SELECT * FROM city WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CityMapper());
    }
}
