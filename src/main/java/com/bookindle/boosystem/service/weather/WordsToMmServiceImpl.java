package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.WordsToMm;
import com.bookindle.boosystem.repository.weather.WordsToMmRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsToMmServiceImpl implements WordsToMmService{
    @Autowired
    WordsToMmRepostory wordsToMmRepostory;

    @Override
    public List<WordsToMm> findAll() {
        return wordsToMmRepostory.findAll();
    }

    @Override
    public void saveWordsToMm(WordsToMm wordsToMm) {
        wordsToMmRepostory.save(wordsToMm);
    }
}
