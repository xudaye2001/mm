package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author xxf
 */
public interface BookService {

    /**
     * 保存
     * @param book
     * @return
     */
    Book saveBook(Book book);


    /**
     * 删除
     * @param id
     */
    void removeBook(long id);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Optional<Book> getBookById(long id);

    /**
     *
     * @return
     */
    List<Book> getAllBooks();
}