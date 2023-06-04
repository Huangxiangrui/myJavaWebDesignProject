package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxr.shopping.pojo.Admin;

public interface AdminService {
    Admin login(Admin admin);
}
