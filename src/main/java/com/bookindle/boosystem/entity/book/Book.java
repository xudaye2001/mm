package com.bookindle.boosystem.entity.book;

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
    @Column(nullable = true)
    private String pubdate;

    @Column(nullable = true)
    private String produce;

    @Column(nullable = true)
    private String page;

    @Column(nullable = true)
    private String price;

    @Column(nullable = true)
    private String format;

    @Column(nullable = true)
    private String img;


    @Column(nullable = true)
    private String paper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    @Size(max = 50)
    private String binding;

    @Column(nullable = true, length = 200)
    private String image;

    @Column(nullable = true)
//    @NotEmpty(message = "ISBN不能为空")
    private String isbn;

    @Column(nullable = true)
//    @NotEmpty(message = "标题不能为空")
    private String title;

    @Column(nullable = true)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Size(max = 1000)
    private String gist;

    @NotEmpty(message = "作者不能为空")
    @Size(min = 2, max = 50)
    private String author;

//    @Size(min = 2, max = 50)
//    @NotEmpty(message = "出版社不能为空")
    @Column(nullable = true)
    private String publisher;



}
