package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.OrderMapper;
import com.hxr.shopping.pojo.Order;
import com.hxr.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Page<Order> orderPage(Page<Order> page, QueryWrapper<Order> wrapper) {
        return orderMapper.selectPage(page,wrapper);
    }

    @Override
    public List<Order> getOrderInfo(QueryWrapper<Order> wrapper) {
        return orderMapper.selectList(wrapper);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int delOneOrder(Order order) {
        return orderMapper.deleteById(order);
    }

    @Override
    public int updOneOrder(Order order) {
        return orderMapper.updateById(order);
    }
}
