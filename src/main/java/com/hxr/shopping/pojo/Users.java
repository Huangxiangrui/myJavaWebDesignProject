package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uid;
    @TableField("u_name")
    private String uname;
    @TableField("u_pass")
    private String upass;
    @TableField("u_status")
    private Integer ustatus;
}