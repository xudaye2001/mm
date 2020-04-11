//package com.bookindle.boosystem.entity.book;
//
//import com.bookindle.boosystem.entity.BaseEntity;
//import com.bookindle.boosystem.entity.user.User;
//import com.bookindle.boosystem.entity.user.UserRole;
//import lombok.Data;
//import org.hibernate.annotations.ColumnDefault;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author xxf
// */
//@Data
//@Entity
//@Table(name = "book")
//public class Book extends BaseEntity implements Serializable {
//    @Column(nullable = true)
//    private String pubdate;
//
//    @Column(nullable = true)
//    private String produce;
//
//    @Column(nullable = true)
//    private String page;
//
//    @Column(nullable = true)
//    private String price;
//
//    @Column(nullable = true)
//    private String format;
//
//    @Column(nullable = true)
//    private String img;
//
//
//    @Column(nullable = true)
//    private String paper;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = true)
//    @Size(max = 50)
//    private String binding;
//
//    @Column(nullable = true, length = 200)
//    private String image;
//
//    @Column(nullable = true)
////    @NotEmpty(message = "ISBN不能为空")
//    private String isbn;
//
//    @Column(nullable = true)
////    @NotEmpty(message = "标题不能为空")
//    private String title;
//
//    @Column(nullable = true)
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Size(max = 1000)
//    private String gist;
//
//    @NotEmpty(message = "作者不能为空")
//    @Size(min = 2, max = 50)
//    private String author;
//
////    @Size(min = 2, max = 50)
////    @NotEmpty(message = "出版社不能为空")
//    @Column(nullable = true)
//    private String publisher;
//
////    @Column(nullable = true)
////    @ColumnDefault("0")
////    private long view = 0;
//
//    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    private List<User> ownners;
//
//    public void setUser(List<User> user) {
//    }
//}
