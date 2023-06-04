package com.hxr.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.ProductInfoMapper;
import com.hxr.shopping.pojo.ProductInfo;
import com.hxr.shopping.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Override
    public Page<ProductInfo> productPage(Page<ProductInfo> page, QueryWrapper<ProductInfo> wrapper) {
        return productInfoMapper.selectPage(page,wrapper);
    }

    @Override
    public ProductInfo getOneProductInfo(Integer id) {
        return productInfoMapper.selectById(id);
    }

    @Override
    public int addProductInfo(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public int delOneProductInfo(ProductInfo productInfo) {
        return productInfoMapper.deleteById(productInfo);
    }

    @Override
    public int updOneProductInfo(ProductInfo productInfo) {
        return productInfoMapper.updateById(productInfo);
    }
}
