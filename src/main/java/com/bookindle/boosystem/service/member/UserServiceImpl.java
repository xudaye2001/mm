package com.bookindle.boosystem.service.member;


import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.repository.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> PageByUser(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }
}
