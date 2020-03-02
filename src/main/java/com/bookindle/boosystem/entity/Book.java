package com.bookindle.boosystem.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author xxf
 */
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    @Size(max = 50)
    private String originTitle;

    @Column(nullable = true)
    private String pubdata;


    @Column(nullable = true)
    @Size(max = 50)
    private String banding;

    @Column(nullable = true)
    @Size(max = 50)
    private String translator;

    @Column(nullable = true, length = 200)
    private String image;

    @Column(nullable = false)
    @NotEmpty(message = "ISBN不能为空")
    private String isbn13;

    @Column(nullable = false)
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Column(nullable = false)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Size(min = 2)
    private String summary;


    @OneToOne(cascade = CascadeType.ALL, targetEntity = Author.class)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Publisher.class)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(Book book) {
        this.isbn13 = book.isbn13;
        this.title = book.title;
        this.summary = book.summary;
        this.author = book.author;
        this.publisher = book.publisher;
    }

    public Book() { }
}
