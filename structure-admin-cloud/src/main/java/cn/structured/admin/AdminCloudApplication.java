package cn.structured.admin;

import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chuck
 */
@EnableSwagger
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AdminCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminCloudApplication.class, args);
    }
}
