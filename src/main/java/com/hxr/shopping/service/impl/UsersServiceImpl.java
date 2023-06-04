package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.AdminMapper;
import com.hxr.shopping.mapper.UsersMapper;
import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.pojo.Users;
import com.hxr.shopping.service.UsersService;
import com.hxr.shopping.utils.MD5Util;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;
    @Override
    public Users login(Users users) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("u_name", users.getUname());
        Users user = usersMapper.selectOne(wrapper);
        if (user != null && MD5Util.getMD5(users.getUpass()).equals(user.getUpass())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public Page<Users> getall(Page<Users> page, QueryWrapper<Users> wrapper) {
        return usersMapper.selectPage(page,wrapper);
    }

    @Override
    public Users getone(int id) {
        return usersMapper.selectById(id);
    }

    @Override
    public int updUsers(Users users) {
        return usersMapper.updateById(users);
    }

    @Override
    public int insert(Users users) {
        return usersMapper.insert(users);
    }
}
