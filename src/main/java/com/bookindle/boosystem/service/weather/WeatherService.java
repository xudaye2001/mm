package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public interface WeatherService {
    Weather findByCityAndDate(City city, Date date);
    void deleteAll();
    void save(Weather weather);
}
