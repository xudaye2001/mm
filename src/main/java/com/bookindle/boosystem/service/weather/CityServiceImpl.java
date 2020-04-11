package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    CityRepostory cityRepostory;

    @Override
    public List<City> findAll() {
        return cityRepostory.findAll();
    }

    @Override
    public void delete(City city) {
        cityRepostory.delete(city);
    }

    @Override
    public void save(City city) {
        cityRepostory.save(city);
    }

    @Override
    public City findByCity(String city) {
        return cityRepostory.findByCity(city);
    }
}
