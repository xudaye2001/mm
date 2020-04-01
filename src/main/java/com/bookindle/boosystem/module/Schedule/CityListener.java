package com.bookindle.boosystem.module.Schedule;

import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.repository.weather.CityListRepostory;
import com.bookindle.boosystem.service.weather.CityListService;
import com.bookindle.boosystem.util.Api.CityListApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class CityListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    CityListRepostory cityListRepostory;

    @Autowired
    CityListService cityListService;

    //项目启动执行
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
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

    //项目终止时执行
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}