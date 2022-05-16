package cn.structured.sa;

import cn.structure.starter.web.restful.annotation.EnableFastJsonHttpConverters;
import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger
@SpringBootApplication
@MapperScan(basePackages = "cn.structured.sa.dao.**")
@EnableFastJsonHttpConverters(longToString = true, nullShowValue = true)
public class StructureAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureAdminApplication.class, args);
    }

}
