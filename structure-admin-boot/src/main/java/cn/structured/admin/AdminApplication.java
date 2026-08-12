package cn.structured.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chuck
 */
@SpringBootApplication
@ComponentScan(basePackages = { "cn.structured.admin", "cn.structured.user" })
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}