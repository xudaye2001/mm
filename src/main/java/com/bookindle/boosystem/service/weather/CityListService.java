package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.CityList;

import java.util.List;
import java.util.Optional;

public interface CityListService {
    List<CityList> findAll();
    void save(CityList city);
    Optional<CityList> findCityById(long id);
}
