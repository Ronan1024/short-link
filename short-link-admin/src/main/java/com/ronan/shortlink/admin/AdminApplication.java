package com.ronan.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台管理应用
 *
 * @program: short-link
 * @author: L.J.Ran
 * @create: 2026/2/27
 */
@SpringBootApplication
@MapperScan("com.ronan.shortlink.admin.dao.mapper")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
