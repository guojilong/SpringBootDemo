package com.example.demo.web;


import com.example.demo.model.User;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {


    @Autowired
    private UserService userService;

    @Value("${neo.title}")
    private String title;

    @RequestMapping("/hello")
    public String hello(String name) {

        return "hello world," + name + " " + title;
    }


    @RequestMapping(name = "/getUser", method = RequestMethod.GET)
    public User getUser() {

        User user = new User();
        user.setName("小明");
        user.setAge(12);
        user.setPass("123456");

        return user;
    }


    @RequestMapping("/getUsers")
    public List<User> getUsers(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                       int pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                       int pageSize) {


        return userService.findAllUser(pageNum, pageSize).getList();
    }
}
