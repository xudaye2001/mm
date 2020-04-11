package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.WordsToMm;


import java.util.List;

public interface WordsToMmService {

    List<WordsToMm> findAll();

    void saveWordsToMm(WordsToMm wordsToMm);
}
