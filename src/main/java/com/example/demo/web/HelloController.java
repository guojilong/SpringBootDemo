package com.example.demo.web;


import com.example.demo.model.User;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${neo.title}")
    private String title;

    @RequestMapping("/hello")
    public String hello(String name) {

        String cacheHello = (String) redisTemplate.opsForValue().get("hello");

        if (cacheHello == null) {

            String sayHi = "hello world," + name + " " + title + "  again!";

            redisTemplate.opsForValue().set("hello", sayHi);
            return "hello world," + name + " " + title;
        }

        return cacheHello;
    }


    @RequestMapping(name = "/getUser", method = RequestMethod.GET)
    public User getUser(String name) {

        User user = null;

        user = (User) redisTemplate.opsForValue().get(name);
        if (user == null) {

            user = new User();
            user.setName(name);
            user.setAge(13);
            user.setPass("123456");

            redisTemplate.opsForValue().set(name, user);
            user.setAge(user.getAge() - 1);
            return user;
        }

        user.setAge(user.getAge()+1);
        redisTemplate.opsForValue().set(name,user);

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
