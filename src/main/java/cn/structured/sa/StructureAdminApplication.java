package cn.structured.sa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.structured.sa.dao.**")
@SpringBootApplication
public class StructureAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureAdminApplication.class, args);
    }

}
