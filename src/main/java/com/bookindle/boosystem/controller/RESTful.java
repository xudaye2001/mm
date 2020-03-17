package com.bookindle.boosystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.book.Book;
import com.bookindle.boosystem.repository.BookRepostory;

import com.show.api.ShowApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class RESTful {
    @Autowired
    private BookRepostory bookRepostory;

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

    /**
     * 添加图书
     * @param rev
     */
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public void addBook(@RequestBody JSONObject rev) {
        Book book = JSONObject.parseObject(String.valueOf(rev), Book.class);
        bookRepostory.save(book);
    }
}
