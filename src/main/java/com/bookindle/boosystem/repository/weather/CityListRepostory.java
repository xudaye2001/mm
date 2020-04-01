package com.bookindle.boosystem.repository.weather;

import com.bookindle.boosystem.entity.weather.CityList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface CityListRepostory extends JpaRepository<CityList, Long>, JpaSpecificationExecutor<CityList> {
}
