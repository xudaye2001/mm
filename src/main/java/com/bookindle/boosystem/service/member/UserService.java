package com.bookindle.boosystem.service.member;


import com.bookindle.boosystem.entity.user.User;
import org.springframework.data.domain.Page;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserService {
    void save(User User);//保存用户
    Page<User> PageByUser(int page, int size);//对用户数据进行分页

}
