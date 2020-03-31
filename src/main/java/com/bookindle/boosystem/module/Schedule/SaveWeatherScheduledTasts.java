package com.bookindle.boosystem.module.Schedule;

import com.bookindle.boosystem.controller.WeatherMsg;
import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.mq.SenderToQueue;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import com.bookindle.boosystem.util.weather.CheckWeatherByCity;
import com.bookindle.boosystem.util.weather.SaveWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableScheduling
public class SaveWeatherScheduledTasts {
    @Autowired
    WeatherRepostory weatherRepostory;
    @Autowired
    CityRepostory cityRepostory;
    @Autowired
    SenderToQueue senderToQueue;

    @Scheduled(cron = "0 30 18 ? * * ")
    public void addWeathers() {
        weatherRepostory.truncateWeather();
        List<City> cityList = cityRepostory.findAll();
        for (int i=0;i<cityList.size();i++) {
            String city = cityList.get(i).getCity();
            SaveWeather saveWeather = new SaveWeather();
            ArrayList<Weather> weathers = saveWeather.saveWeathers(city);
            City cityObject = cityRepostory.findByCity(city);
            for (int j=0;j<weathers.size();j++) {
                Weather weather = weathers.get(j);
                weather.setCity(cityObject);
                weatherRepostory.save(weather);
            }
        }
    }

    @Scheduled(cron = "0 40 18 ? * * ")
    public void getMessage() {
        Date dateToday = new Date();

        // 获取明日日历
        Date dateTomorrow = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTomorrow);
        calendar.add(Calendar.DATE, 1);
        dateTomorrow = calendar.getTime();

        // 获取城市列表
        List<City> cityList = cityRepostory.findAll();

        CheckWeatherByCity checkWeatherByCity = new CheckWeatherByCity();
        for(City city : cityList) {
            Weather weatherToday =  weatherRepostory.findByCityAndDate(city, dateToday);
            Weather weatherTomorrow =  weatherRepostory.findByCityAndDate(city, dateTomorrow);
            String res = checkWeatherByCity.checkWeatherAndSendRabbitMQ(weatherToday,weatherTomorrow,city.getCity());
            Set<User> userList =  city.getUserList();

            if(res != null) {
                for (User user:userList) {
                    Map<String, String> contentList = new HashMap<>();
                    String mobile =  user.getMobile();
                    String cnName = user.getCnname();
                    String resFront = "【绵绵细雨】"+ user.getCnname();

                    if (cnName != null) {
                        res = resFront+res;
                    }else {
                        res = "【绵绵细雨】绵绵小朋友"+res;
                    }
                    contentList.put("mobile",mobile);
                    contentList.put("content", res);
                    this.senderToQueue.send(contentList);
                }
            }
        }
    }

}
