package com.bookindle.boosystem.util.weather;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
public class SaveWeather {

    public SaveWeather(){}

    public ArrayList<Weather> saveWeathers(String city) {
        HttpRequestWeatherMsg weatherMsg = new HttpRequestWeatherMsg();
        JSONObject msg = weatherMsg.getNewWeather(city);

        JSONArray dailys =  msg.getJSONArray("daily");
        ArrayList<Weather> weathers = new ArrayList<>();
        for (int i=0; i<dailys.size(); i++) {
            JSONObject daily = dailys.getJSONObject(i);
            JSONObject dailyNight = daily.getJSONObject("night");
            JSONObject dailyDay = daily.getJSONObject("day");

            Weather weather = new Weather();

            weather.setDate(daily.getDate("date"));
            weather.setWeek(daily.getString("week"));

            weather.setDayTempow(dailyDay.getString("temphigh"));
            weather.setDayWeather(dailyDay.getString("weather"));
            weather.setDayWindpower(dailyDay.getString("windpower"));

            weather.setNightTemplow(dailyNight.getString("templow"));
            weather.setNightWeather(dailyNight.getString("weather"));
            weather.setNightWindpower(dailyNight.getString("windpower"));

            weathers.add(weather);
        }
        return weathers;
    }
}
