package com.example.demo.service.user.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {

        return userDao.insert(user);
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectUsers();
        PageInfo result = new PageInfo(users);
        return result;
    }
}
