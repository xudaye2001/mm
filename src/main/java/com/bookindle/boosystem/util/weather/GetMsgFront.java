package com.bookindle.boosystem.util.weather;

import org.springframework.stereotype.Component;

@Component
public class GetMsgFront {
    public String getMsgFronByCnname(String cnname, String res) {
        // 返回消息体
        String newRes = "";

        // 短信模板
        String model = "【绵绵细雨】";

        // 定制语料
        String mineRes = "<=哥哥说他好爱你呀=>";

        if (cnname.equals("绵绵小朋友")) {
            newRes = model + cnname + "," + res +mineRes;
        }else  {
            newRes = model + cnname + "," + res;
        }

        return newRes;

    }
}
