package com.bookindle.boosystem.module.Schedule;


import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.repository.member.UserRepository;

import com.bookindle.boosystem.service.weather.CityListService;
import com.bookindle.boosystem.util.Api.CityListApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityListener   {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityListService cityListService;

    //项目启动执行
    @PostConstruct
    public void contextInitialized() {
        long startTime = System.currentTimeMillis();
        logger.info("开始执行启动任务,{获取城市列表并写入数据库及缓存}"+startTime);
        //业务处理
        CityListApi cityListApi = new CityListApi();
        List<String> cityListString = new ArrayList<>();
        cityListString = cityListApi.getCityList();
        for (int i=0;i<cityListString.size();i++) {
            CityList city = new CityList();
            city.setCitySingle(cityListString.get(i));
            cityListService.save(city);
        }
        long endTime = System.currentTimeMillis();
        logger.info("执行启动任务结束,共花费时间{}"+(startTime-endTime));
    }

}