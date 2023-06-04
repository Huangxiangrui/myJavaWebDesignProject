package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.pojo.Users;
import org.apache.catalina.User;

public interface UsersService {
    Users login(Users users);
    Page<Users> getall(Page<Users> page, QueryWrapper<Users> wrapper);
    Users getone(int id);
    int updUsers(Users users);
    int insert(Users users);
}
