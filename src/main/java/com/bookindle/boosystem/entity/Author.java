package com.bookindle.boosystem.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author xxf
 */
@Entity
@Data
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "作者不能为空")
    @Size(min = 2, max = 10)
    private String author;

    public Author() {
    }
}
