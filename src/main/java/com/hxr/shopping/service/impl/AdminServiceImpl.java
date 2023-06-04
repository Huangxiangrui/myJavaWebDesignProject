package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxr.shopping.mapper.AdminMapper;
import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.service.AdminService;
import com.hxr.shopping.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("a_name", admin.getAname());
        Admin ad = adminMapper.selectOne(wrapper);
        if (ad != null && MD5Util.getMD5(admin.getApass()).equals(ad.getApass())) {
            return ad;
        } else {
            return null;
        }
    }
}
