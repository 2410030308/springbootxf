package com.yuan.service.impl;

import com.yuan.entity.Order;
import com.yuan.mapper.OrderMapper;
import com.yuan.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
