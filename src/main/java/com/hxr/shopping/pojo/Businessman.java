package com.hxr.shopping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("businessman")
public class Businessman implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "b_id",type = IdType.AUTO)
    private Integer bid;
    @TableField("b_name")
    private String bname;
    @TableField("b_pass")
    private String bpass;
    @TableField("b_status")
    private Integer bstatus;

}