package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.ProductInfo;

import java.awt.print.Book;

public interface ProductInfoService {
//    商品分页展示
    Page<ProductInfo> productPage(Page<ProductInfo>page, QueryWrapper<ProductInfo>wrapper);
    ProductInfo getOneProductInfo(Integer id);
    int addProductInfo(ProductInfo productInfo);
    int delOneProductInfo(ProductInfo productInfo);
    int updOneProductInfo(ProductInfo productInfo);
}
