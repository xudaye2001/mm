package com.bookindle.boosystem.util;


import com.show.api.ShowApiRequest;

public class getBookFromApi {
    public void getBookFromApi(String isbn) {
        String appid="159368";
        String appsecret="15343717fa4a424d872fea3713db7d7a";

        String res=new ShowApiRequest("http://route.showapi.com/1626-1",appid,appsecret)
                .addTextPara("isbn",isbn)
                .post();
        System.out.println(res);
    }


}
