package cn.structured.admin.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动装配入口
 *
 * @author chuck
 * @since JDK1.8
 */
@Configuration
@Import( AutoAdminDefaultConfiguration.class)
public class AutoAdminConfiguration {

}
