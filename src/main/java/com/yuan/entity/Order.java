package com.yuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
@TableId(value = "oid",type = IdType.ASSIGN_UUID)
    private String oid;

    private String ordertime;

    private BigDecimal total;

    private Integer status;

    private String address;

    private String uid;


}
