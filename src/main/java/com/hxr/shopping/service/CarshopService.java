package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Carshop;
import com.hxr.shopping.pojo.ProductInfo;

public interface CarshopService {
    Page<Carshop> carshopPage(Page<Carshop>page, QueryWrapper<Carshop> wrapper);
    Carshop getOneCarshopInfo(QueryWrapper<Carshop> wrapper);
    int addCarshop(Carshop carshop);
    int delOneCarshop(Carshop carshop);
    int updOneCarshop(Carshop carshop);
}
