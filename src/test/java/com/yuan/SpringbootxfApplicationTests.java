package com.yuan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuan.entity.User;
import com.yuan.mapper.UserMapper;
import com.yuan.service.AdminService;
import com.yuan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;

@SpringBootTest
class SpringbootxfApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminService adminService;
    @Test
    void contextLoads() {
        LocalDateTime datetime = LocalDateTime.now();System.out.println("当前日期 : " + datetime.plusMinutes(1));
        Instant timestamp = Instant.now();System.out.println("当前时间戳 : " + timestamp.getEpochSecond());
    }

    @Test
    public void md5() {
        

        User str = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, "str"));

        System.out.println("str = " + str);
        //测试spring框架封装的Md5
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("111111".getBytes());
        System.out.println("md5:"+md5DigestAsHex);
    }


}
