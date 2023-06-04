package com.hxr.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.mapper.UsersMapper;
import com.hxr.shopping.pojo.*;
import com.hxr.shopping.service.CarshopService;
import com.hxr.shopping.service.OrderService;
import com.hxr.shopping.service.ProductInfoService;
import com.hxr.shopping.service.UsersService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    final private int PAGESIZE = 5;
    @Autowired
    UsersService usersService;

    @PostMapping("/apply")
    public R applybussinessman(@RequestBody Users users) {
        int flag = -1;
        try {
            flag = usersService.updUsers(users);
            if (flag > 0) {
                return new R(Code.WORK_OK,"申请成功",users);
            } else {
                return new R(Code.WORK_ERR,"申请失败",users);
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"无效！",users);
        }
    }
    @PostMapping("/getoneuser")
    public R getOneUser(@RequestBody Users user) {
        Users users = usersService.getone(user.getUid());
        if(users==null){
            return new R(Code.WORK_ERR,"查找为空");
        }
        else{
            return new R(Code.WORK_OK,"查找成功",users);
        }
    }
}
