package com.example.demo.service.user;

import com.example.demo.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    int addUser(User user);

    PageInfo<User> findAllUser(int pageNum,int pageSize);
}
