package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aid;
    @TableField("a_name")
    private String aname;
    @TableField("a_pass")
    private String apass;

}