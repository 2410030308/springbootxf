package com.yuan.service.impl;

import com.yuan.entity.Book;
import com.yuan.mapper.BookMapper;
import com.yuan.service.BookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
