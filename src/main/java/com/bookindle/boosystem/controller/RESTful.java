package com.bookindle.boosystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.book.Book;

import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.repository.book.BookRepostory;
import com.bookindle.boosystem.repository.weather.CityListRepostory;
import com.bookindle.boosystem.service.book.BookService;
import com.bookindle.boosystem.service.weather.CityListService;
import com.show.api.ShowApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class RESTful {
    @Autowired
    private BookRepostory bookRepostory;

    @Autowired
    private BookService bookService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    @Autowired
////    CityListRepostory cityListRepostory;

    @Autowired
    CityListService cityListService;

    /**
     * 获取图书列表
     * @return
     */
    @RequestMapping(value = "/booklist",method = RequestMethod.GET)
    public List<Book> getBook() {
        return bookRepostory.findAll();
    }

    /**
     * 手动添加图书
     * @param book
     */
    @RequestMapping(value = "/addbookfromhand", method = RequestMethod.POST)
    public void addBooks(@RequestBody Book book) {
        bookRepostory.save(book);
    }

    /**
     * 通过ISBN获取图书信息
     * @param isbn
     * @return
     */
    @RequestMapping(value = "/addbookfromisbn", method = RequestMethod.POST)
    public String addBookFromIsbn(@RequestBody JSONObject isbn) {
        String appid="159368";
        String appsecret="15343717fa4a424d872fea3713db7d7a";


        String res=new ShowApiRequest("http://route.showapi.com/1626-1",appid,appsecret)
                .addTextPara("isbn", (String) isbn.get("isbn"))
                .post();
        return res;
    }

    @GetMapping(value = "/bookdetails/{id}")
    public Book getBookDetails(
            @PathVariable("id") Long id
    ) {
        Book book = bookService.findBookById(id);
        return book;
    }

    @Cacheable(cacheNames = "getInputCityList")
    @PostMapping(value = "/getInputCityList")
    public String getInputCityList() {
        List<CityList> cityLists = new ArrayList<>();
        cityLists = cityListService.findAll();
        return JSON.toJSONString(cityLists);
    }
}
