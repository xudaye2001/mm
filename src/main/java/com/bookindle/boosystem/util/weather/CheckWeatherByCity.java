package com.bookindle.boosystem.util.weather;

import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.mq.SenderToQueue;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import com.google.api.client.util.Lists;
import org.apache.tomcat.websocket.WsRemoteEndpointAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CheckWeatherByCity {

    public String checkWeatherAndSendRabbitMQ(Weather todayWeather, Weather tomorrowWeather, String city, Date dateTomorrow) {
        Date dateToday = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("阵雨,雷阵雨,雷阵雨伴有冰雹,雨夹雪,小雨,中雨,大雨,暴雨,大暴雨," +
                "特大暴雨,阵雪,小雪, 中雪,大雪,暴雪,雾,冻雨,沙尘暴,小到中雨,中到大雨,大到暴雨,\n" +
                "暴雨到大暴雨,大暴雨到特大暴雨,小到中雪, 中到大雪, 大到暴雪, 浮尘,扬沙,强沙尘暴,霾");
        String str = sb.toString();
        String[] parts = str.split(",");
        List weatherList = Arrays.asList(parts);
        String res = "明天"+dateTomorrow.getDate()+"日"+city+"=>";
        // 最高温差
        int temperatureDifferent =  Integer.parseInt(tomorrowWeather.getDayTempow()) - Integer.parseInt(todayWeather.getDayTempow());
        int windPower = Integer.parseInt(tomorrowWeather.getDayWindpower().replace("级","").replace("微风","1").replace("-", ""));
        int settingTem = 5;
        int settingWin = 4;
        String stuTemRes = "今日气温:"+todayWeather.getDayTempow()+"°"+"明日气温:"+tomorrowWeather.getDayTempow()+"°,";

        Boolean sendMsg = false;

        if (temperatureDifferent <= -settingTem) {
            String temRes = "降温"+temperatureDifferent+"°.";
            res = res+stuTemRes+temRes;
            sendMsg = true;
        }
        if (temperatureDifferent >= settingTem) {
            String temRes = "升温"+temperatureDifferent+"°.";
            res = res+stuTemRes+temRes;
            sendMsg = true;
        }
        if (windPower>=settingWin) {
            String windPowerString = String.valueOf(windPower);
            if (windPowerString.length()>=2) {
                windPowerString = windPowerString.substring(0,1)+"至"+windPowerString.substring(1,2);
            }

            String windRes = "风力"+windPowerString+"级.";
            res = res+windRes;
            sendMsg = true;
        }

        String weatherRes = tomorrowWeather.getDayWeather();
        if (weatherList.contains(weatherRes)) {
            String weatherResinf = "天气:"+weatherRes+".";
            res = res+weatherResinf;
            sendMsg = true;
        }

        if (sendMsg ==true) {
            return res;
        }else {
            return null;
        }
    }
}
