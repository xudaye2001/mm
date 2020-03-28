package com.bookindle.boosystem.util.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;

import java.util.*;

public class getSevenDayWeatherMsg {

    private String city;

	public getSevenDayWeatherMsg() {

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
//		JSONArray jsonArray = jsonObject.getJSONArray("daily");



		System.out.println(jsonObject);
		return jsonObject ;
	}

}




