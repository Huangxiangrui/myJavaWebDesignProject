package com.hxr.shopping.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Businessman;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BusinessmanService {
    Businessman login(Businessman businessman);
    Page<Businessman> getAll(Page<Businessman> page, QueryWrapper<Businessman> wrapper);
    List<Businessman> getlist(QueryWrapper<Businessman> wrapper);
    int addBusinessman(Businessman businessman);
    int updBusinessman(Businessman businessman);

}
