//package com.bookindle.boosystem.controller;
//
//import com.bookindle.boosystem.entity.Author;
//import com.bookindle.boosystem.entity.book.Book;
//import com.bookindle.boosystem.entity.Publisher;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//public class indexTest {
//    @Autowired
//    private WebApplicationContext webContext;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setupMockMvc() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
//    }
//
//    @Test
//    public void homePage()throws Exception{
//        mockMvc.perform(get("/index"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attributeExists("booklist"));
//    }
//
//    @Test
//    public void addBook()throws Exception{
////        Publisher publisher = new Publisher();
////        publisher.setPublisher("中信出版社");
////        Author author = new Author();
////        author.setAuthor("徐大爷");
//
//        mockMvc.perform(post("/addbook")
//                .param("titile", "我的自传")
//                .param("author", "徐大爷")
//                .param("publisher", "中信出版社")
//                .param("isbn", "1234567890123"))
//                .andExpect(status().is3xxRedirection()).andExpect(header().string("location", "/index"));
//    }
//}
