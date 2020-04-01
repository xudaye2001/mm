package com.bookindle.boosystem.util.Api;

import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.repository.weather.CityRepostory;
import com.bookindle.boosystem.repository.weather.WeatherRepostory;
import com.bookindle.boosystem.util.weather.CheckWeatherByCity;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class SendTelephoneMsg {

    public void sendMsgToUserMobile(String mobile, String content) {
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/kaixintong/kaixintong");
        model.setAppkey("5593797f4045f1b711882449801b195b");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("mobile",mobile);
        queryMap.put("content",content);
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        String res = call.request();
        System.out.println(res);
        System.out.println("向"+mobile+"发送了"+content);
    }
}
