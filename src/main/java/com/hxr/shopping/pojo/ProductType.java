package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("product_type")
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "type_id",type = IdType.AUTO)
    private Integer typeId;
    @TableField("type_name")
    private String typeName;


}