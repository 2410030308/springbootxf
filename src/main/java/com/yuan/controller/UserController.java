package com.yuan.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.common.Result;
import com.yuan.entity.User;
import com.yuan.mapper.UserMapper;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.yuan.Config.DescribeInstances.sendShortMessage;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2021-01-26 22:53:35
 */
@RestController
@RequestMapping("User")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    UserService userService;
    @Resource
    private UserMapper userMapper;

    @PostMapping("register")
    public String register(@PathParam("user") User user) {
        User one = userService.getOne(new QueryWrapper<>(user));
        if (one != null) {
            return "login";
        } else {

            String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(pwd);
            return userService.save(user) ? "page/login-1.html" : "404";
        }
    }

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 8);
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        session.setAttribute("captcha", captcha.getCode());
        //获取当前时间 ，存入session
        session.setAttribute("codeTime", LocalDateTime.now());
        // 输出到页面
        captcha.write(response.getOutputStream());
        // 打印日志
        logger.info("生成的验证码:{}", captcha.getCode());
        // 关闭流
        response.getOutputStream().close();
    }

    @PostMapping("/login")

    @ResponseBody
    public Result login(@PathParam("username") String username,
                        @PathParam("password") String password,
                        @PathParam("captcha") String captcha,
                        HttpServletRequest request, HttpSession session) {
        if (captcha == null) {
            return Result.error("验证码已失效，请重新输入");
        }
        String code = session.getAttribute("captcha").toString();
        if (captcha == null || code == null || code.isEmpty() || !captcha.equalsIgnoreCase(code)) {
            return Result.error("验证码错误");
        } else if (((LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) - ((LocalDateTime) session.getAttribute("codeTime")).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) / 1000 / 60 > 2) {
            return Result.error("验证码已过期，重新获取");
        } else {
            //验证成功，删除存储的验证码
            session.removeAttribute("captcha");
            session.removeAttribute("codeTime");
            String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username), false);
            System.out.println(pwd);
            if (user != null && user.getPassword().equals(pwd)) {
                return Result.success(null);
            } else {
                return Result.error("-1");
            }
        }
    }

    /*
     * 短信验证登入
     * */
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @PostMapping("/getSms")
    public Result getSms(@PathParam("phoneNumber") String phoneNumber) {
        //模板ID
        String templateID = "869024";
        //随机生成6位验证码
        String param = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //过期时间
        Integer time = 1;
        //存入Redis并设置过期时间为1分钟
        redisTemplate.boundListOps("phoneCode").rightPushAll(phoneNumber, param);
        redisTemplate.expire("phoneCode", time, TimeUnit.MINUTES);
        //发短信
        String message = sendShortMessage(templateID, phoneNumber, param, time.toString());
        System.out.println(message);
        return Result.success("成功发送验证码！");
    }

    @ResponseBody
    @PostMapping("/smsLogin")
    public Result smsLogin(@PathParam("phoneNumber") String phoneNumber,
                           @PathParam("phoneCode") String phoneCode) {
        String redisphoneNumber = (String) redisTemplate.boundListOps("phoneCode").index(0);
        String redisphoneCode = (String) redisTemplate.boundListOps("phoneCode").index(1);
        System.out.println("code = " + redisphoneCode + redisphoneNumber);
        if (redisphoneNumber.isEmpty() || redisphoneCode.isEmpty()) {
            return Result.error("请重新获取短信验证码！");
        } else if (phoneNumber.equals(redisphoneNumber) || redisphoneCode.equals(phoneCode)) {
            return Result.success("登入成功！");
        }
        return Result.error("请输入正确的验证码！");
    }

    /**
     * 分页查询所有数据
     *
     * @param page  分页对象
     * @param tUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<User> page, User tUser) {
        return Result.success("成功！", this.userService.page(page, new QueryWrapper<>(tUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody User tUser) {
        return Result.success(this.userService.save(tUser));
    }

    /**
     * 修改数据
     *
     * @param tUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody User tUser) {
        return Result.success(this.userService.updateById(tUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.userService.removeByIds(idList));
    }
}