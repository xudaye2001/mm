package com.bookindle.boosystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.service.weather.CityService;
import com.bookindle.boosystem.service.weather.WeatherService;
import com.bookindle.boosystem.util.weather.SaveWeather;
import com.bookindle.boosystem.util.Api.HttpRequestWeatherMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class WeatherMsg {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public JSONObject getWeather(@RequestBody String city) {
        HttpRequestWeatherMsg weatherMsg = new HttpRequestWeatherMsg();
        JSONObject msg = weatherMsg.getNewWeather(city);
        return msg;
    }

    /**
     * 根据城市保存/更新天气
     */
    @RequestMapping(value = "/updataweathers", method = RequestMethod.POST)
    public void addWeathers() {
        List<City> cityList = cityService.findAll();
        for (int i=0;i<cityList.size();i++) {
            String city = cityList.get(i).getCity();


            SaveWeather saveWeather = new SaveWeather();
            ArrayList<Weather> weathers = saveWeather.saveWeathers(city);
            City cityObject = cityService.findByCity(city);



            for (int j=0;j<weathers.size();j++) {
                Weather weather = weathers.get(j);
                weather.setCity(cityObject);
                weatherService.save(weather);
            }
        }
    }
}
