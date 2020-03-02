package com.bookindle.boosystem.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class addBookControllerTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

//    @Test
////    public void homepage() throws Exception{
////
////    }

    @Test
    public void addBook() throws Exception {
        mockMvc.perform(post("/addbook")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "大爷自传")
                .param("publisher", "中华书局")
                .param("isbn", "56789087")
                .param("author", "徐大爷"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location", "booklist"));
    }
}
