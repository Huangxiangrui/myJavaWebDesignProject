package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("product_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "o_id",type = IdType.AUTO)
    private Integer oid;
    @TableField("u_id")
    private Integer uid;
    @TableField("b_id")
    private Integer bid;
    @TableField("p_id")
    private Integer pid;
    @TableField("totalprice")
    private Double totalprice;
    @TableField("status")
    private Integer status;
    @TableField("p_name")
    private String pname;

    @TableField("numbers")
    private Integer numbers;
}