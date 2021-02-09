package com.yuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuan
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cartitem")
public class Cartitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cartitemid",type = IdType.ASSIGN_UUID)
    private String cartitemid;

    private Integer quantity;

    private String bid;

    private String uid;

    @TableField("orderBy")
    private Integer orderby;


}
