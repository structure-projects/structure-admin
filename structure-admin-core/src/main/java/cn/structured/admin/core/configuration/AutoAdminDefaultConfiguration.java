package cn.structured.admin.core.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.structured.admin.core.**")
@MapperScan(basePackages = "cn.structured.admin.core.mapper.**")
public class AutoAdminDefaultConfiguration {
}
