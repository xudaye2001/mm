package com.mm.mm.controller;


import com.alibaba.fastjson.JSONObject;
import com.mm.mm.utils.getWeatherMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class WeatherMsg {

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public String getWeather(@RequestBody JSONObject Msg) {
        getWeatherMsg weatherMsg = new getWeatherMsg();
        String msg = weatherMsg.getNewWeather(Msg);
        return msg;
    }


    @GetMapping(value = "/")
    public String getWeather1(@RequestBody JSONObject Msg) {
        getWeatherMsg weatherMsg = new getWeatherMsg();
        String msg = weatherMsg.getNewWeather(Msg);
        return msg;
    }

}
