package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.City;

import java.util.List;

public interface CityService {
    City findByCity(String city);
    void save(City city);
    void delete(City city);
    List<City> findAll();
}
