package com.hxr.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxr.shopping.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order>{}