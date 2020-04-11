package com.bookindle.boosystem.repository.weather;

import com.bookindle.boosystem.entity.weather.Weather;
import com.bookindle.boosystem.entity.weather.WordsToMm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WordsToMmRepostory extends JpaRepository<WordsToMm, Long>, JpaSpecificationExecutor<WordsToMm> {
}
