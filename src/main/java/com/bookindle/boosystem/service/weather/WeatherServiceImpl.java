package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepostory weatherRepostory;

    @Override
    public Weather getWeatherByDate(Date date) {
        return weatherRepostory.findByDate(date);
    }

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepostory.save(weather);
    }
}
