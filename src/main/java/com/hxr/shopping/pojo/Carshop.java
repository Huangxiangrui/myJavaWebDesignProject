package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("carshop")
public class Carshop implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "c_id",type = IdType.AUTO)
    private Integer cid;
    @TableField("u_id")
    private Integer uid;
    @TableField("p_id")
    private Integer pid;
    @TableField("b_id")
    private Integer bid;
    @TableField("numbers")
    private Integer numbers;
    @TableField("p_name")
    private String pname;
}