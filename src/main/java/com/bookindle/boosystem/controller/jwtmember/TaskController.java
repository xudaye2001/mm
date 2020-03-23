package com.bookindle.boosystem.controller.jwtmember;
import com.alibaba.fastjson.JSONObject;
import com.bookindle.boosystem.entity.book.Book;
import com.bookindle.boosystem.entity.user.User;
import com.bookindle.boosystem.repository.book.BookRepostory;
import com.bookindle.boosystem.repository.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longzhonghua
 * @data 3/5/2019 8:52 PM
 */
@RestController
@RequestMapping("/restful/tasks")
public class TaskController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepostory bookRepostory;

    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

    /**
     * 获取用户名
     * @return
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public String newTasks(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }



    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public void addBook(@RequestBody JSONObject rev) {
        Book book = JSONObject.parseObject(String.valueOf(rev), Book.class);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(userName);
        List<User> owner = new ArrayList<>();
        owner.add(user);
        book.setOwnners(owner);
        bookRepostory.save(book);
    }
}