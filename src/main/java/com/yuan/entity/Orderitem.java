package com.yuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_orderitem")
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "orderItemId",type = IdType.ASSIGN_UUID)
    private String orderitemid;

    private Integer quantity;

    private BigDecimal subtotal;

    private String bid;

    private String bname;

    @TableField("currPrice")
    private BigDecimal currprice;

    private String imageB;

    private String oid;


}
