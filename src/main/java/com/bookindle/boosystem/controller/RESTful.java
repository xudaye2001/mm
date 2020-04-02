package com.bookindle.boosystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.book.Book;

import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.user.UserRole;
import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.repository.book.BookRepostory;
import com.bookindle.boosystem.repository.member.UserRepository;
import com.bookindle.boosystem.repository.weather.CityListRepostory;
import com.bookindle.boosystem.service.book.BookService;
import com.bookindle.boosystem.service.weather.CityListService;
import com.bookindle.boosystem.util.Api.CityListApi;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityListService cityListService;

    /**
     * 获取图书列表
     *
     * @return
     */
    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public List<Book> getBook() {
        return bookRepostory.findAll();
    }

    /**
     * 手动添加图书
     *
     * @param book
     */
    @RequestMapping(value = "/addbookfromhand", method = RequestMethod.POST)
    public void addBooks(@RequestBody Book book) {
        bookRepostory.save(book);
    }

    /**
     * 通过ISBN获取图书信息
     *
     * @param isbn
     * @return
     */
    @RequestMapping(value = "/addbookfromisbn", method = RequestMethod.POST)
    public String addBookFromIsbn(@RequestBody JSONObject isbn) {
        String appid = "159368";
        String appsecret = "15343717fa4a424d872fea3713db7d7a";


        String res = new ShowApiRequest("http://route.showapi.com/1626-1", appid, appsecret)
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
        if (cityLists != null) {
            return JSON.toJSONString(cityLists);
        } else {
            CityListApi cityListApi = new CityListApi();
            List<String> cityListString = new ArrayList<>();
            cityListString = cityListApi.getCityList();
            for (int i = 0; i < cityListString.size(); i++) {
                CityList city = new CityList();
                city.setCitySingle(cityListString.get(i));
                cityListService.save(city);
                cityLists = cityListService.findAll();
                return JSON.toJSONString(cityLists);
            }
        }
        return JSON.toJSONString(cityLists);
//        UserRole userRole = new UserRole();
//        userRole.setRolename("ROLE_USER");
//        List<UserRole> userRoleList = new ArrayList<>();
//        userRoleList.add(userRole);
//
//
//        User usergege = new User();
//        usergege.setCnname("哥哥");
//        usergege.setMobile("18982222373");
//        usergege.setPassword("$2a$10$8gzT3nO89dVnMnd.WZVJSenZQWlklnINmNRtWavUfJnJkup8ptmQ.");
//        usergege.setName("test2");
//        usergege.setRoles(userRoleList);
//        userRepository.save(usergege);
//
//        User usermianmian = new User();
//        usermianmian.setCnname("波澜绵绵");
//        usermianmian.setMobile("18982222374");
//        usermianmian.setPassword("$2a$10$8gzT3nO89dVnMnd.WZVJSenZQWlklnINmNRtWavUfJnJkup8ptmQ.");
//        usermianmian.setName("test4");
//        usermianmian.setRoles(userRoleList);
//        userRepository.save(usermianmian);
//
//
//        User userlaoli = new User();
//        userlaoli.setCnname("老李");
//        userlaoli.setMobile("18982222375");
//        userlaoli.setPassword("$2a$10$8gzT3nO89dVnMnd.WZVJSenZQWlklnINmNRtWavUfJnJkup8ptmQ.");
//        userlaoli.setName("test5");
//        userlaoli.setRoles(userRoleList);
//        userRepository.save(userlaoli);
//
//        User userweiwei = new User();
//        userweiwei.setCnname("伟伟");
//        userweiwei.setMobile("18982222376");
//        userweiwei.setPassword("$2a$10$8gzT3nO89dVnMnd.WZVJSenZQWlklnINmNRtWavUfJnJkup8ptmQ.");
//        userweiwei.setName("test6");
//        userweiwei.setRoles(userRoleList);
//        userRepository.save(userweiwei);
    }
}
