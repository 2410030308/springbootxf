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
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 登入密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 男/女
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Boolean status;

    @TableField("activationCode")
    private String activationcode;


}
