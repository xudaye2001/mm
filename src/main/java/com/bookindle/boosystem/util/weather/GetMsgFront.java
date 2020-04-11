package com.bookindle.boosystem.util.weather;

import com.bookindle.boosystem.entity.weather.WordsToMm;
import com.bookindle.boosystem.service.weather.WordsToMmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetMsgFront {

    @Autowired
    WordsToMmService wordsToMmService;

    public String getMsgFronByCnname(String cnname, String res,List<WordsToMm> wordsToMmList) {
        // 返回消息体
        String newRes = "";

        // 短信模板
        String model = "【绵绵细雨】";

        // 定制语料



        int index = (int) (Math.random() * wordsToMmList.size());

        String mineRes = wordsToMmList.get(index).getWords();


        if (cnname.equals("绵绵小朋友")) {
            newRes = model + cnname + "," + res +mineRes;
        }else  {
            newRes = model + cnname + "," + res;
        }

        return newRes;

    }
}
