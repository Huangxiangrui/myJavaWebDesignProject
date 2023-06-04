package com.hxr.shopping.controller;

import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.pojo.Users;
import com.hxr.shopping.service.AdminService;
import com.hxr.shopping.service.BusinessmanService;
import com.hxr.shopping.service.UsersService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.MD5Util;
import com.hxr.shopping.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginContorller {
    @Autowired
    AdminService adminService;
    @Autowired
    BusinessmanService businessmanService;
    @Autowired
    UsersService usersService;
    @PostMapping("/admin")
    public R login(@RequestBody Admin admin) {
        Admin ad = adminService.login(admin);
        if (ad != null) {
            return new R(Code.WORK_OK,"登陆成功",ad);
        } else {
            return new R(Code.WORK_ERR,"登陆失败");
        }
    }
    @PostMapping("/user")
    public R login(@RequestBody Users users){
        Users user = usersService.login(users);
        if(user!=null){
            return new R(Code.WORK_OK,"登陆成功",user);
        }else{
            return new R(Code.WORK_ERR,"登陆失败");
        }
    }
    @PostMapping("/bussiness")
    public R login(@RequestBody Businessman bussinessman){
        Businessman businessman1 = businessmanService.login(bussinessman);
        if(businessman1!=null){
            return new R(Code.WORK_OK,"登陆成功",businessman1);
        }else{
            return new R(Code.WORK_ERR,"登陆失败");
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody Users user){
        user.setUpass(MD5Util.getMD5(user.getUpass()));
        int flag=-1;
        try {
            flag=usersService.insert(user);
            if (flag > 0) {
                return new R(Code.WORK_OK,"注册成功",user);
            } else {
                return new R(Code.WORK_ERR,"注册失败");
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"无法注册！");
        }
    }

}
