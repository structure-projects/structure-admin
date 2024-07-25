package cn.structured.admin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动装配入口
 *
 * @author chuck
 * @since JDK1.8
 */
@Configuration
@Import({AutoMybatisConfiguration.class, AutoAdminDefaultConfiguration.class})
public class AutoAdminConfiguration {

}
