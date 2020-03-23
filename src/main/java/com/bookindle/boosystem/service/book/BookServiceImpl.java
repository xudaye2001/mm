package com.bookindle.boosystem.service.book;

import com.bookindle.boosystem.entity.book.Book;
import com.bookindle.boosystem.repository.book.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "bookService")
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepostory bookRepostory;

    @Override
    @CachePut(value = "book_put", key ="#p0" )
    public Book saveBook(Book book) {
        return bookRepostory.save(book);
    }

    @Override
    public void removeBook(long id) {
        bookRepostory.deleteById(id);

    }

    @Override
    @Cacheable(value = "book_fid", key="#p0")
    public Book findBookById(long id)
    {
        return  bookRepostory.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepostory.findAll();
        return books;
    }
}
