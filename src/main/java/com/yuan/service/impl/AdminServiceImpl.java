package com.yuan.service.impl;

import com.yuan.entity.Admin;
import com.yuan.mapper.AdminMapper;
import com.yuan.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuan
 * @since 2021-01-26
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
