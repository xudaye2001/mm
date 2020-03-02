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
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    @Size(min = 2, max = 50)
    @NotEmpty(message = "出版社不能为空")
    private String publisher;

    public Publisher() {

    }
}
