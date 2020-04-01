package com.bookindle.boosystem.service.weather;

import com.bookindle.boosystem.entity.weather.CityList;
import com.bookindle.boosystem.repository.weather.CityListRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@CacheConfig(cacheNames = "CityListServiceImpl")
public class CityListServiceImpl implements CityListService {
    @Autowired
    CityListRepostory cityListRepostory;

    @Override
    public void save(CityList city) {
        cityListRepostory.save(city);
    }

    @Override
//    @Cacheable
    public Optional<CityList> findCityById(long id) {
        return cityListRepostory.findById(id);
    }

    @Cacheable(value = "findAll",keyGenerator="wiselyKeyGenerator")
    @Override
    public List<CityList> findAll() {
        return cityListRepostory.findAll();
    }
}
