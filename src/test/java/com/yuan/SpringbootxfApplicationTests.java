package com.yuan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuan.entity.User;
import com.yuan.mapper.UserMapper;
import com.yuan.service.AdminService;
import com.yuan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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
/*
* 腾讯短信验证
* */
  /*  @Test
    public void tengxun() {
            String templateID = "869024";
            String phoneNumbers = "15579884897";
            //随机生成6位验证码
            String param=(int)((Math.random()*9+1)*100000)+"";
            String time="1";
            sendShortMessage(templateID,phoneNumbers,param,time);
        }*/
    @Test
    public void code() {
        String random=(int)((Math.random()*9+1)*100000)+"";
        System.out.println("创建的验证码为："+random);

    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void redis() {
        redisTemplate.boundListOps("listKey").rightPushAll("1","2","3");
        redisTemplate.expire("listKey",1, TimeUnit.MINUTES);
        String listKey4 = (String) redisTemplate.boundListOps("1").index(1);
        System.out.println(listKey4);
    }

}
