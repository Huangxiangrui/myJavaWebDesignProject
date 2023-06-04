package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("product_info")
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "p_id",type = IdType.AUTO)
    private Integer pid;
    @TableField("p_name")
    private String pname;
    @TableField("p_content")
    private String pcontent;
    @TableField("p_price")
    private Integer pprice;
    @TableField("p_image")
    private String pimage;
    @TableField("p_number")
    private Integer pnumber;
    @TableField("type_id")
    private Integer typeId;
    @TableField("b_id")
    private Integer bid;
}