package cn.structured.admin.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.structured.admin.**")
@MapperScan(basePackages = "cn.structured.admin.mapper.**")
public class AutoAdminDefaultConfiguration {
}
