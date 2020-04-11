package com.bookindle.boosystem.entity.weather;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class WordsToMm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String words;
}
