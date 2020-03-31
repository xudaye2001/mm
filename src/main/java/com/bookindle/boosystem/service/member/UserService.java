package com.bookindle.boosystem.service.member;


import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.entity.weather.City;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserService {

    void addCity(User user, List<City> city);

    User findUserByName(String username);
    Page<User> PageByUser(int page, int size);//对用户数据进行分页

}
