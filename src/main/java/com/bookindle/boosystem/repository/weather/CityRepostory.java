package com.bookindle.boosystem.repository.weather;

import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;



public interface CityRepostory extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    City findByCity(String city);

    List<City> findAll();
}
