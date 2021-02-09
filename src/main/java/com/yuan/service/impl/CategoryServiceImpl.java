package com.yuan.service.impl;

import com.yuan.entity.Category;
import com.yuan.mapper.CategoryMapper;
import com.yuan.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
