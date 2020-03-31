package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.Weather;

import java.sql.Date;

public interface WeatherService {

    Weather getWeatherByDate(Date date);

    Weather saveWeather(Weather weather);

}
