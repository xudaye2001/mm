package com.bookindle.boosystem.module.Schedule;

import com.bookindle.boosystem.controller.WeatherMsg;
import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.mq.SenderToQueue;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import com.bookindle.boosystem.util.weather.CheckWeatherByCity;
import com.bookindle.boosystem.util.weather.GetMsgFront;
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



    /**
     * 更新城市天气
     */
    @Scheduled(cron = "0 50 18 * * ?")
    public void addWeathers() {
        System.out.println("获取所有城市天气");


        // 获取所有城市
        List<City> cityList = cityRepostory.findAll();
        // 删除之前天气信息
        weatherRepostory.deleteAll();

        // 遍历城市列表
        for (int i=0;i<cityList.size();i++) {
            String city = cityList.get(i).getCity();
            SaveWeather saveWeather = new SaveWeather();

            //根据城市名称获取当前城市7日天气
            ArrayList<Weather> weathers = saveWeather.saveWeathers(city);

            City cityObject = cityRepostory.findByCity(city);
            for (int j=0;j<weathers.size();j++) {
                Weather weather = weathers.get(j);
                weather.setCity(cityObject);


                weatherRepostory.save(weather);
            }
        }
    }

    /**
     * 根据城市名向用户发送短信
     */
//    @Scheduled(cron = "0 0/5 * * * ?")
    public void sendUserWeatherMsg(City city) {
        System.out.println("发送短信");
        // 获取城市列表
            String res = city.getMsg();
            Set<User> userList = city.getUserList();
            if(res != null) {

                for (User user:userList) {

                    String mobile =  user.getMobile();
                    String cnName = user.getCnname();

                    // 处理头部消息
                    GetMsgFront getMsgFront = new GetMsgFront();
                    String newRes = getMsgFront.getMsgFronByCnname(cnName , res);

                    // 交给中间件分发
                    Map<String, String> contentList = new HashMap<>();
                    contentList.put("mobile",mobile);
                    contentList.put("content", newRes);
                    this.senderToQueue.send(contentList);
                }
            }
    }

    /**
     * 解析天气并写入城市Msg
     */
    @Scheduled(cron = "0 00 19 * * ?")
    public void getMessage() {
        System.out.println("解析天气");
        Date dateToday = new Date();

        // 获取明日日历
        Date dateTomorrow = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTomorrow);
        calendar.add(Calendar.DATE, 1);
        dateTomorrow = calendar.getTime();

        // 获取城市列表
        List<City> cityList = cityRepostory.findAll();

        // 遍历城市列表解析天气数据
        CheckWeatherByCity checkWeatherByCity = new CheckWeatherByCity();
        for (City city : cityList) {

             Weather weatherToday = weatherRepostory.findByCityAndDate(city, dateToday);
             Weather weatherTomorrow = weatherRepostory.findByCityAndDate(city, dateTomorrow);

            String res = checkWeatherByCity.checkWeatherAndSendRabbitMQ(weatherToday, weatherTomorrow, city.getCity(),dateTomorrow);
            if (res != null) {
                city.setMsg(res);
                this.sendUserWeatherMsg(city);
            } else {
                city.setMsg(null);
            }
            // 解析结果持久化
            cityRepostory.save(city);
        }
    }
}