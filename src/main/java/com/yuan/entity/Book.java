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
@TableName("t_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "bid",type = IdType.ASSIGN_UUID)
    private String bid;

    private String bname;

    private String author;

    private BigDecimal price;

    @TableField("currPrice")
    private BigDecimal currprice;

    private BigDecimal discount;

    private String press;

    private String publishtime;

    private Integer edition;

    @TableField("pageNum")
    private Integer pagenum;

    @TableField("wordNum")
    private Integer wordnum;

    private String printtime;

    private Integer booksize;

    private String paper;

    private String cid;

    private String imageW;

    private String imageB;

    @TableField("orderBy")
    private Integer orderby;


}
