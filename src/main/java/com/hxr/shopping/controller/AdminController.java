package com.hxr.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Admin;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.pojo.Users;
import com.hxr.shopping.service.AdminService;
import com.hxr.shopping.service.BusinessmanService;
import com.hxr.shopping.service.UsersService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin")
public class AdminController {
    final private int PAGESIZE = 5;
    @Autowired
    BusinessmanService businessmanService;
    @Autowired
    UsersService usersService;
    @PostMapping("/listbuss/{current}")
    public Page<Businessman> getAll(@PathVariable long current, @RequestBody Businessman businessman) {
        Page<Businessman> page = new Page<>(current, PAGESIZE);
        QueryWrapper<Businessman> wrapper = new QueryWrapper<>();
        if(businessman.getBname()!=null&&businessman.getBname()!=""){
            wrapper.like("b_name",businessman.getBname());
        }
        if (businessman.getBstatus() != null) {
            wrapper.eq("b_status", businessman.getBstatus());
        }
        return businessmanService.getAll(page, wrapper);
    }

    @PostMapping("/upd")
    public R updBussinessman(@RequestBody Businessman businessman) {
        int flag = -1;
        try {
            flag = businessmanService.updBusinessman(businessman);
            if (flag > 0) {
                return new R(Code.WORK_OK,"更新成功");
            } else {
                return new R(Code.WORK_ERR,"更新失败");
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"无法更新！");
        }
    }
    @PostMapping("/listuser/{current}")
    public Page<Users> getUsers(@PathVariable("current") long current,@RequestBody Users users){
        Page<Users> page = new Page<>(current,PAGESIZE);
        QueryWrapper<Users> wrapper= new QueryWrapper<>();
        wrapper.eq("u_status",1);
        return usersService.getall(page,wrapper);
    }
    @PostMapping("/add")
    public R addBussinessman(@RequestBody Users users) {
        int flag = -1;
        int flag1=-1;
        users = usersService.getone(users.getUid());
        Businessman businessman = new Businessman();
        businessman.setBname(users.getUname());
        System.out.println(businessman.getBname());
        businessman.setBpass(users.getUpass());
        businessman.setBstatus(0);
        try {
            flag = businessmanService.addBusinessman(businessman);
            users.setUstatus(2);
            flag1=usersService.updUsers(users);
            if (flag > 0&& flag1 > 0) {
                return new R(Code.WORK_OK,"申请成功");
            } else {
                return new R(Code.WORK_ERR,"申请失败");
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"发生错误...");
        }
    }
}
