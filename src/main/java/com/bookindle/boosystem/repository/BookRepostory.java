package com.bookindle.boosystem.repository;

import com.bookindle.boosystem.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xxf
 */
public interface BookRepostory extends JpaRepository<Book, Long> {
}
