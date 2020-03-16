package com.bookindle.boosystem.controller;

import com.bookindle.boosystem.entity.Author;
import com.bookindle.boosystem.entity.Book;
import com.bookindle.boosystem.entity.Publisher;
import com.bookindle.boosystem.repository.AuthorRepostory;
import com.bookindle.boosystem.repository.BookRepostory;
import com.bookindle.boosystem.repository.PublisherRepostory;
import com.bookindle.boosystem.util.ConstraintViolationExceptionHandler;
import com.bookindle.boosystem.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BookRepostory bookRepostory;
    @Autowired
    PublisherRepostory publisherRepostory;
    @Autowired
    AuthorRepostory authorRepostory;

    @RequestMapping(value = "/index")
    public ModelAndView booklist(){
        List<Book> bookList = bookRepostory.findAll();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("booklist",bookList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/addbook")
    public String addBook(String title, String isbn, String publisher, String author){

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setAuthor(author);
        bookRepostory.save(book);
        return "redirect:/index/";
    }
}
