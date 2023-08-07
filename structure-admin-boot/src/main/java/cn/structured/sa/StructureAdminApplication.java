package cn.structured.sa;

import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chuck
 */
@EnableSwagger
@SpringBootApplication
public class StructureAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureAdminApplication.class, args);
    }

}
