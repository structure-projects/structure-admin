package cn.structured.sa.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 环境的配置
 *
 * @author cqliut
 * @version 2022.0223
 * @since 1.0.1
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "structure.tenant")
public class TenantProperty {

    /**
     * 默认租户ID
     */
    private String defaultTenantId = "1";
}
