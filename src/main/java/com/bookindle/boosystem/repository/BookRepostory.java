package com.bookindle.boosystem.repository;

import com.bookindle.boosystem.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author xxf
 */
public interface BookRepostory extends JpaRepository<Book, Long> {
    Optional<Book> findById(String id);
}
