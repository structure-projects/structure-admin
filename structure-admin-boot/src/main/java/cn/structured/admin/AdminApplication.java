package cn.structured.admin;

import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chuck
 */
@EnableSwagger
@SpringBootApplication
@ComponentScan(basePackages = "cn.structured.**")
@MapperScan(basePackages = "cn.structured.user.mapper")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}