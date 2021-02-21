package com.yuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 86155
 */
@SpringBootApplication
@MapperScan("com.yuan.mapper")
@EnableScheduling
public class SpringbootxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootxfApplication.class, args);
    }

}
