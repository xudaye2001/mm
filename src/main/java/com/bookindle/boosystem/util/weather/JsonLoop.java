package com.bookindle.boosystem.util.weather;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonLoop {
    private static List<Map<String, String>> list = new ArrayList<>();
    private static Map resDay = new HashMap();


    public static ArrayList jsonLoop(Object object) {


        if(object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry: jsonObject.entrySet()) {
                Object o = entry.getValue();
                if(o instanceof String) {
//                    System.out.println("key:" + entry.getKey() + "，value:" + entry.getValue());
                    resDay.put(entry.getKey(),entry.getValue());
                } else {
                    jsonLoop(o);
                }

            }
        }
        if(object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for(int i = 0; i < jsonArray.size(); i ++) {
                jsonLoop(jsonArray.get(i));

            }
            list.add(resDay);
        }

        return (ArrayList) list;
    }

}
