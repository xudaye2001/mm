package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.Author;

public interface AuthorService  {
    /**
     * 保存作者
     * @return
     */
    Author saveAuthor(Author author);
}
