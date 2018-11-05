package com.example.demo.web;


import com.example.demo.model.BaseResp;
import com.example.demo.model.User;
import com.example.demo.service.user.UserService;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


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
    public BaseResp<User> getUser(String name, @RequestParam(name = "pageNum", required = false, defaultValue = "1")
            int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                          int pageSize) {

        User user = null;

        if (StringUtils.isEmpty(name)) {

            return Response.err("name cannot be null or empty", 500);
        }

        user = (User) redisTemplate.opsForValue().get(name);
        if (user == null) {

            List<User> users = userService.findUserByName(name, pageNum, pageSize).getList();

            if (CollectionUtils.isEmpty(users)) {


                return Response.err("user not exist!", 500);
            }

            user = users.get(0);
            redisTemplate.opsForValue().set(name, user);
            return Response.ok(user);
        }

        return Response.ok(user);
    }


    @RequestMapping("/getUsers")
    public BaseResp<List<User>> getUsers(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                       int pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                       int pageSize) {

        return Response.ok(userService.findAllUser(pageNum, pageSize).getList());
    }


    @RequestMapping("/create")
    public BaseResp<User> createUser(String name, String pwd) {

        if (StringUtils.isEmpty(name)) {

            return Response.err("name cannot be null or empty", 500);
        }

        if (StringUtils.isEmpty(pwd)) {
            return Response.err("password cannot be null or empty", 500);
        }

        int size = userService.findUserByName(name, 1, 10).getSize();

        if (size>0) {

            return Response.err("name exist",500);
        }


        User user=new User();
        user.setName(name);
        user.setPass(pwd);
        userService.addUser(user);

        return Response.ok(user);
    }
}
