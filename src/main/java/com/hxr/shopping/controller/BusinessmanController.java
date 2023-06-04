package com.hxr.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.pojo.Order;
import com.hxr.shopping.pojo.ProductInfo;
import com.hxr.shopping.service.BusinessmanService;
import com.hxr.shopping.service.OrderService;
import com.hxr.shopping.service.ProductInfoService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("businessman")
public class BusinessmanController {
    @Autowired
    BusinessmanService businessmanService;
    @PostMapping("/getBs")
    public R getOneBs() {
        List<Businessman> list = businessmanService.getlist(new QueryWrapper<>());
        Map<Integer, Object> map = new HashMap<>();
        if (list != null) {
            for (Businessman product : list) {
                map.put(product.getBid(), product.getBname());
            }
            return new R(Code.WORK_OK, "getlist", map);
        } else {
            return new R(Code.WORK_ERR, "noBussinessman");
        }
    }
}
