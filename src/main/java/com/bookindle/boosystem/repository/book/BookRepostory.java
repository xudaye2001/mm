package com.bookindle.boosystem.repository.book;

import com.bookindle.boosystem.entity.book.Book;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.Optional;

/**
 *
 */
public interface BookRepostory extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findById(long id);

    @Modifying
    @Transactional
    @Query("update Book set view = :view where id =:id")
    int updateBookViewById(
            @Param("view") long view, @Param("id")long id
    );

}
