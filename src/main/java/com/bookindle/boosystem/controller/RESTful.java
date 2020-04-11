package com.bookindle.boosystem.controller;

import com.alibaba.fastjson.JSON;

import com.bookindle.boosystem.entity.weather.CityList;

import com.bookindle.boosystem.repository.member.UserRepository;
import com.bookindle.boosystem.service.weather.CityListService;
import com.bookindle.boosystem.util.Api.CityListApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class RESTful {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityListService cityListService;


    @PostMapping(value = "/getInputCityList")
    public String getInputCityList() {
        List<CityList> cityLists = new ArrayList<>();
        cityLists = cityListService.findAll();
        if (cityLists != null) {
            return JSON.toJSONString(cityLists);
        } else {
            CityListApi cityListApi = new CityListApi();
            List<String> cityListString = new ArrayList<>();
            cityListString = cityListApi.getCityList();
            for (int i = 0; i < cityListString.size(); i++) {
                CityList city = new CityList();
                city.setCitySingle(cityListString.get(i));
                cityListService.save(city);
                cityLists = cityListService.findAll();
                return JSON.toJSONString(cityLists);
            }
        }
        return JSON.toJSONString(cityLists);
    }
}
