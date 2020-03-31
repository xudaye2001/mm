package com.bookindle.boosystem.repository.weather;

import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityRepostory extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    City findByCity(String city);
}
