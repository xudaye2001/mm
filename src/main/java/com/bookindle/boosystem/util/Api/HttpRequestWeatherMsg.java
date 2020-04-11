package com.bookindle.boosystem.util.Api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HttpRequestWeatherMsg {
	public HttpRequestWeatherMsg() {
	}

	/**
	 * 返回最近7天天气
	 * @param city
	 * @return
	 */
	public JSONObject getNewWeather(String city) {
		RequestModel model = new RequestModel();

		model.setGwUrl("https://way.jd.com/jisuapi/weather");
		model.setAppkey("5593797f4045f1b711882449801b195b");
		Map queryMap = new HashMap();
		queryMap.put("city", city);
		model.setQueryParams(queryMap);
		WxApiCall call = new WxApiCall();
		call.setModel(model);
//		call.request();

		String msg = call.request();


		JSONObject jsonObject = JSON.parseObject(msg);
		jsonObject = jsonObject.getJSONObject("result").getJSONObject("result");

		System.out.println(jsonObject);
		return jsonObject ;
	}

}




