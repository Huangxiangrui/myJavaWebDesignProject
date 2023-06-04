package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.BusinessmanMapper;
import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.service.BusinessmanService;
import com.hxr.shopping.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessmanServiceImpl implements BusinessmanService {
    @Autowired
    BusinessmanMapper businessmanMapper;

    @Override
    public Businessman login(Businessman businessman) {
        QueryWrapper<Businessman> wrapper = new QueryWrapper<>();
        wrapper.eq("b_name", businessman.getBname());
        Businessman businessman1 = businessmanMapper.selectOne(wrapper);
        if (businessman1 != null && MD5Util.getMD5(businessman.getBpass()).equals(businessman1.getBpass())) {
            return businessman1;
        } else {
            return null;
        }
    }

    @Override
    public Page<Businessman> getAll(Page<Businessman> page, QueryWrapper<Businessman> wrapper) {
        return businessmanMapper.selectPage(page, wrapper);
    }

    @Override
    public List<Businessman> getlist(QueryWrapper<Businessman> wrapper) {
        return businessmanMapper.selectList(wrapper);
    }

    @Override
    public int addBusinessman(Businessman businessman) {
        return businessmanMapper.insert(businessman);
    }

    @Override
    public int updBusinessman(Businessman businessman) {
        return businessmanMapper.updateById(businessman);
    }

}
