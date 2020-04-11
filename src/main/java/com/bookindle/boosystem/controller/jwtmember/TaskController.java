package com.bookindle.boosystem.controller.jwtmember;
import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.mq.SenderToQueue;

import com.bookindle.boosystem.service.member.UserService;
import com.bookindle.boosystem.service.weather.CityListService;
import com.bookindle.boosystem.service.weather.CityService;
import com.bookindle.boosystem.service.weather.WeatherService;
import com.bookindle.boosystem.util.weather.CheckWeatherByCity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 需验证的请求
 */
@RestController
@RequestMapping("/restful/tasks")
public class TaskController {
    @Autowired
    private UserService userService;

    @Autowired
    private SenderToQueue senderToQueue;

    @Autowired
    private CityListService cityListService;

    @Autowired
    private CityService cityService;

    @Autowired
    private WeatherService weatherService;



    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

    /**
     * 获取用户名
     * @return
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public String newTasks(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userRequest = userService.findByName(userName);
        return userRequest.getCnname();
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }

    @RequestMapping(value = "/deletedcity", method = RequestMethod.POST)
    public void deletedCity(@RequestBody JSONObject cityListNew) {
        // 获取用户名及请求城市
        String originUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        String originCityName = cityListNew.getString("city");;
        // 查找用户User和CIty
        User originUser = userService.findByName(originUserName);
        City originCity = cityService.findByCity(originCityName);

        Set<User> userList = originCity.getUserList();
        userList.remove(originUser);
        originCity.setUserList(userList);
        cityService.save(originCity);

        // 获取用户城市列表
        Set<City> cityList = originUser.getCityList();
        // 移除用户请求城市
        cityList.remove(originCity);
        // 保存新的城市列表
        originUser.setCityList(cityList);
        // 持久化用户
        userService.save(originUser);

        userList = cityService.findByCity(originCityName).getUserList();
        if (userList.size() == 0) {
            cityService.delete(originCity);
        }
    }


    @RequestMapping(value = "/addcity", method = RequestMethod.PATCH)
    public void addCity(@RequestBody JSONObject cityListNew) {
        String originUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        String originCityName = cityListNew.getString("city");

        // 获取数据库中的标准城市名称
        List<CityList> cityListDataBase = cityListService.findAll();

        //设置
        Boolean citySame = false;
        for (int i=0;i<cityListDataBase.size();i++) {
            String cityListDataBaseName = cityListDataBase.get(i).getCitySingle();
            if (originCityName.equals(cityListDataBaseName)) {
                citySame = true;
                break;
            }
        }
        if (!citySame) {
            for (int i=0;i<cityListDataBase.size();i++) {
                String cityListDataBaseName = cityListDataBase.get(i).getCitySingle();
                if (cityListDataBaseName.contains(originCityName) || originCityName.contains(cityListDataBaseName)) {
                    originCityName = cityListDataBaseName;
                    citySame = true;
                    break;
                }
            }
        }
        User originUser = userService.findByName(originUserName);
        City originCity = cityService.findByCity(originCityName);
            // 增加城市
            if (originCity !=null) {
                // 给User增加城市
                Set<City> cityList = new HashSet<>();
                cityList.add(originCity);
                originUser.setCityList(cityList);
                userService.save(originUser);

                // 给城市增加user
                Set<User> userList =  originCity.getUserList();
                userList.add(originUser);
                originCity.setUserList(userList);

                cityService.save(originCity);

            }else {
                // 增加城市
                originCity = new City();
                originCity.setCity(originCityName);

                Set<User> userList = new HashSet<>();
                userList.add(originUser);
                originCity.setUserList(userList);
                cityService.save(originCity);

                // 保存用户
                Set<City> cityList = new HashSet<>();
                cityList = originUser.getCityList();
                cityList.add(originCity);
                originUser.setCityList(cityList);
                userService.save(originUser);
            }
    }

    @RequestMapping(value = "/getcitylist", method = RequestMethod.POST)
    public List<String> getCityList() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<City> userCityList = user.getCityList();
        Iterator<City> cityIterator = userCityList.iterator();
        List<String> cityList = new ArrayList<>();
        while (cityIterator.hasNext()) {
            cityList.add(cityIterator.next().getCity());
        }
        return cityList;
    }

    @RequestMapping(value = "/checkallweathers", method = RequestMethod.POST)
    public void getMessage() {
        Date dateToday = new Date();

        // 获取明日日历
        Date dateTomorrow = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTomorrow);
        calendar.add(Calendar.DATE, 1);
        dateTomorrow = calendar.getTime();

        // 获取城市列表
        List<City> cityList = cityService.findAll();

        CheckWeatherByCity checkWeatherByCity = new CheckWeatherByCity();
        for(City city : cityList) {
            Weather weatherToday =  weatherService.findByCityAndDate(city, dateToday);
            Weather weatherTomorrow =  weatherService.findByCityAndDate(city, dateTomorrow);
            String res = checkWeatherByCity.checkWeatherAndSendRabbitMQ(weatherToday,weatherTomorrow,city.getCity(), dateTomorrow);
            Set<User> userList =  city.getUserList();
            if (res !=null) {
                city.setMsg(res);
            }else {
                city.setMsg(null);
            }
            cityService.save(city);
            city = cityService.findByCity(city.getCity());
            res = city.getMsg();
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