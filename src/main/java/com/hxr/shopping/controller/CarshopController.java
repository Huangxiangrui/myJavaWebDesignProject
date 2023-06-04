package com.hxr.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Carshop;
import com.hxr.shopping.pojo.Order;
import com.hxr.shopping.service.CarshopService;
import com.hxr.shopping.service.OrderService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carshop")
public class CarshopController {
    @Autowired
    CarshopService carshopService;
    final private int PAGESIZE = 5;

    @PostMapping("/getcarshoplist/{current}")
    public R getlist(@PathVariable long current, @RequestBody Carshop carshop) {
        Page<Carshop> page = new Page<>(current, PAGESIZE);
        QueryWrapper<Carshop> wrapper = new QueryWrapper<>();
        if (carshop.getPname() != null && carshop.getPname() != "") {
            wrapper.like("p_name", carshop.getPname());
        }
        if (carshop.getUid() != null) {
            wrapper.eq("u_id", carshop.getUid());
        }
        Page<Carshop> page1 = carshopService.carshopPage(page, wrapper);
        if (page1 != null) {
            return new R(Code.WORK_OK, "查询成功", page1);
        } else {
            return new R(Code.WORK_ERR, "查询失败");
        }
    }

    @PostMapping("/addcarshop")
    public R addCarshop(@RequestBody Carshop carshop) {
        QueryWrapper<Carshop> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", carshop.getUid());
        wrapper.eq("p_id", carshop.getPid());
        Carshop carshop1 = carshopService.getOneCarshopInfo(wrapper);
        int flag = -1;
        try {
            if (carshop1 == null) {
                System.out.println("------");
                flag = carshopService.addCarshop(carshop);
                System.out.println("flag"+flag);
            }
            else {
                int numbers=carshop1.getNumbers()+carshop.getNumbers();
                carshop1.setNumbers(numbers);
                flag=carshopService.updOneCarshop(carshop1);
                System.out.println("****"+flag);
            }
            if (flag > 0) {
                return new R(Code.WORK_OK, "添加成功", carshop);
            } else {
                return new R(Code.WORK_ERR, "添加失败", carshop);
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR, "添加失效");
        }
    }

    @PostMapping("/del")
    public R delCarshop(@RequestBody Carshop carshop) {
        int flag = -1;
        try {
            flag = carshopService.delOneCarshop(carshop);
            if (flag > 0) {
                return new R(Code.WORK_OK, "删除成功", carshop);
            } else {
                return new R(Code.WORK_OK, "删除失败", carshop);
            }
        } catch (Exception e) {
            return new R(Code.WORK_OK, "删除失效");
        }
    }

    @PostMapping("/upd")
    public R updCarshop(@RequestBody Carshop carshop) {
        int flag = -1;
        try {
            flag = carshopService.updOneCarshop(carshop);
            if (flag > 0) {
                return new R(Code.WORK_OK, "更新成功", carshop);
            } else {
                return new R(Code.WORK_OK, "更新失败", carshop);
            }
        } catch (Exception e) {
            return new R(Code.WORK_OK, "更新失效");
        }
    }

}
