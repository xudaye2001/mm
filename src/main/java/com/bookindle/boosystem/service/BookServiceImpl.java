package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.book.Book;
import com.bookindle.boosystem.repository.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepostory bookRepostory;

    @Override
    public Book saveBook(Book book) {
        return bookRepostory.save(book);
    }

    @Override
    public void removeBook(long id) {
        bookRepostory.deleteById(id);

    }

    @Override
    public Optional<Book> getBookById(long id)
    {
        return  bookRepostory.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepostory.findAll();
        return books;
    }
}
