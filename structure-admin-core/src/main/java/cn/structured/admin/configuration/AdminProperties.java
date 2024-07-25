package cn.structured.admin.configuration;

import cn.structured.basic.api.enums.FileSystem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * create by chuck 2024/5/26
 *
 * @author chuck
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "structure.admin")
public class AdminProperties {

    /**
     * 上传存储路径 文件存储默认策略，后缀 + 日期 + 文件全称
     */
    private String uploadPath = "/file";

    /**
     * 文件系统存储类型
     */
    private FileSystem fileSystem = FileSystem.LOCAL;

    /**
     * http://localhost:18000
     */
    private String host = "http://localhost:18000";

}
