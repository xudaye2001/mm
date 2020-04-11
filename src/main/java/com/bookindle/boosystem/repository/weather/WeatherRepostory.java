package com.bookindle.boosystem.repository.weather;

import com.bookindle.boosystem.entity.weather.City;
import com.bookindle.boosystem.entity.weather.Weather;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

/**
 *
 */
public interface WeatherRepostory extends JpaRepository<Weather, Long>, JpaSpecificationExecutor<Weather> {
    Weather findById(long id);
    Weather findByDate(Date date);
    Weather findByCityAndDate(City city, Date date);
    void deleteAllByCity(City city);
    Weather findByCity(String city);

//    @Modifying
//    @Transactional
//    @Query("update Book set view = :view where id =:id")
//    int updateBookViewById(
//            @Param("view") long view, @Param("id")long id
//    );

//    @Transactional
//    @Modifying
//    @Query(value = "truncate weather",nativeQuery = true)
//    void truncateWeather();
}