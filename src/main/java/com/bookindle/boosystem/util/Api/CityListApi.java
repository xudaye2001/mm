package com.bookindle.boosystem.util.Api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CityListApi {

    public List<String> getCityList() {
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/jisuapi/weather1");
        model.setAppkey("5593797f4045f1b711882449801b195b");
        Map queryMap = new HashMap();
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        String res = call.request();
        JSONObject resBody = JSONObject.parseObject(res);
        JSONArray resFinal = resBody.getJSONObject("result").getJSONArray("result");
        List<String> cityList = new ArrayList<>();
        for (int i=0;i<resFinal.size();i++) {
            cityList.add(resFinal.getJSONObject(i).getString("city"));
        }
        System.out.println("请求城市列表成功");
        return cityList;
    }


}
