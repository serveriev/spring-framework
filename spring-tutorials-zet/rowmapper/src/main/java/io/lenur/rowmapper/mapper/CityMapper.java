package io.lenur.rowmapper.mapper;

import io.lenur.rowmapper.model.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new City(rs.getLong("id"), rs.getString("name"), rs.getInt("population"));
    }
}
