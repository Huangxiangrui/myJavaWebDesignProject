package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxr.shopping.mapper.ProductTypeMapper;
import com.hxr.shopping.pojo.ProductType;
import com.hxr.shopping.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
    @Autowired
    ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectList(new QueryWrapper<ProductType>());
    }
}
