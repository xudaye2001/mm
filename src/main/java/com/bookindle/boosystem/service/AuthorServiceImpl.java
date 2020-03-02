package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.Author;
import com.bookindle.boosystem.repository.AuthorRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    AuthorRepostory authorRepostory;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepostory.save(author);
    }
}

