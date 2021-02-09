package com.yuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 86155
 */
@SpringBootApplication
@MapperScan("com.yuan.mapper")
public class SpringbootxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootxfApplication.class, args);
    }

}
