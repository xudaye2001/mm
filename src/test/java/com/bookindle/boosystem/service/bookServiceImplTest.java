package com.bookindle.boosystem.service;


import com.bookindle.boosystem.entity.Author;
import com.bookindle.boosystem.entity.Book;
import com.bookindle.boosystem.entity.Publisher;
import com.bookindle.boosystem.repository.AuthorRepostory;
import com.bookindle.boosystem.repository.BookRepostory;
import com.bookindle.boosystem.repository.PublisherRepostory;
import com.bookindle.boosystem.service.AuthorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class bookServiceImplTest {
    @Autowired
    private BookRepostory bookRepostory;
//    @Autowired
//    private AuthorRepostory authorRepostory;
//    @Autowired
//    private PublisherRepostory publisherRepostory;

    @Test
    public void testSaveBook(){

        Author author1 = new Author();
        author1.setAuthor("徐大爷");
//        authorRepostory.save(author1);


        Publisher publisher1 = new Publisher();
        publisher1.setPublisher("中信出版社");
//        publisherRepostory.save(publisher1);




        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setPublisher(publisher1);

        book1.setIsbn13("1111111111111");
        book1.setSummary("个人自传");
        book1.setTitle("<我的自传>");

        bookRepostory.save(book1);

        Long bookId = book1.getId();
        System.out.print(bookId);
    }
}
