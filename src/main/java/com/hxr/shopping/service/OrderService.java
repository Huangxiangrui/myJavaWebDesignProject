package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Carshop;
import com.hxr.shopping.pojo.Order;

import java.util.List;

public interface OrderService {
    Page<Order> orderPage(Page<Order>page, QueryWrapper<Order> wrapper);
    List<Order> getOrderInfo(QueryWrapper<Order> wrapper);
    int addOrder(Order order);
    int delOneOrder(Order order);
    int updOneOrder(Order order);
}
