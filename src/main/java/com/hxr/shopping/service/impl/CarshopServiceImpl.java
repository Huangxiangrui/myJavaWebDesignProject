package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.CarshopMapper;
import com.hxr.shopping.pojo.Carshop;
import com.hxr.shopping.service.CarshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarshopServiceImpl implements CarshopService {
    @Autowired
    CarshopMapper carshopMapper;
    @Override
    public Page<Carshop> carshopPage(Page<Carshop> page, QueryWrapper<Carshop> wrapper) {
        return carshopMapper.selectPage(page,wrapper);
    }

    @Override
    public Carshop getOneCarshopInfo(QueryWrapper<Carshop> wrapper) {
        return carshopMapper.selectOne(wrapper);
    }

    @Override
    public int addCarshop(Carshop carshop) {
        return carshopMapper.insert(carshop);
    }

    @Override
    public int delOneCarshop(Carshop carshop) {
        return carshopMapper.deleteById(carshop);
    }

    @Override
    public int updOneCarshop(Carshop carshop) {
        return carshopMapper.updateById(carshop);
    }
}
