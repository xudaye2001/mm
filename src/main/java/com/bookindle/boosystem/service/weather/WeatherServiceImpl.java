package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepostory weatherRepostory;

    @Override
    public Weather findByCityAndDate(City city, Date date) {
        return weatherRepostory.findByCityAndDate(city, date);
    }

    @Override
    public void save(Weather weather) {
        weatherRepostory.save(weather);
    }

    @Override
    public void deleteAll() {
        weatherRepostory.deleteAll();
    }
}
