package com.bookindle.boosystem.repository;

import com.bookindle.boosystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepostory extends JpaRepository<Author, Long> {
}
