package com.hxr.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Order;
import com.hxr.shopping.service.OrderService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class orderController {
    final private int PAGESIZE = 5;
    @Autowired
    OrderService orderService;

    @PostMapping("/orderlist/{current}")
    public R getOrderList(@PathVariable("current") long current, @RequestBody Order order) {
        Page<Order> page = new Page<>(current, PAGESIZE);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (order.getUid() != null) {
            wrapper.eq("u_id", order.getUid());
        }
        if (order.getBid() != null) {
            wrapper.eq("b_id", order.getBid());
        }
        if(order.getStatus()!=null){
            wrapper.eq("status",order.getStatus());
        }
        if(order.getPname()!=null&&order.getPname()!=""){
            wrapper.like("p_name",order.getPname());
        }
        Page<Order> orderList = orderService.orderPage(page, wrapper);
        return new R(Code.WORK_OK, "显示成功", orderList);
    }
    @PostMapping("/isReady")
    public R getAllReadyOrder(@RequestBody Order order){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.ne("status","3");
        wrapper.eq("p_id",order.getPid());
        wrapper.eq("b_id",order.getBid());
        List<Order> orderList = orderService.getOrderInfo(wrapper);
        if(orderList.isEmpty()){
            return new R(Code.WORK_OK,"所有订单已处理好");
        }else {
            return new R(Code.WORK_ERR,"仍有订单未处理",orderList);
        }
    }
    @PostMapping("/addorder")
    public R addorder(@RequestBody Order order){
        int flag=-1;
        try {
            flag = orderService.addOrder(order);
            if (flag > 0) {
                return new R(Code.WORK_OK,"添加订单成功",order);
            } else {
                return new R(Code.WORK_ERR,"添加订单失败");
            }
        } catch (Exception e) {
            return new R (Code.WORK_ERR,"添加订单无效");
        }
    }
    @PostMapping("/updorder")
    public R updOrder(@RequestBody Order order){
        int flag=-1;
        try {
            flag = orderService.updOneOrder(order);
            if (flag > 0) {
                return new R(Code.WORK_OK,"更新订单成功",order);
            } else {
                return new R(Code.WORK_ERR,"更新订单失败");
            }
        } catch (Exception e) {
            return new R (Code.WORK_ERR,"更新订单无效");
        }
    }
    @PostMapping("delorder")
    public R delOrder(@RequestBody Order order){
        int flag=-1;
        try {
            flag = orderService.delOneOrder(order);
            if (flag > 0) {
                return new R(Code.WORK_OK,"更新订单成功",order);
            } else {
                return new R(Code.WORK_ERR,"更新订单失败");
            }
        } catch (Exception e) {
            return new R (Code.WORK_ERR,"更新订单无效");
        }
    }
}
