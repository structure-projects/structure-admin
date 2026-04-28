package cn.structured.admin.biz.configuration;

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
     * 上传功能是否启用,生产环境请关闭，生产环境建议放置到对象存储系统。很多系统漏洞的都是因为文件上传功能，请关闭上传功能。
     */
    private Boolean uploadEnabled = true;

    /**
     * 上传存储路径 文件存储默认策略，后缀 + 日期 + 文件全称
     */
    private String uploadPath = "/tmp/file";

    /**
     * 文件系统存储类型
     */
    private FileSystem fileSystem = FileSystem.LOCAL;

    /**
     * http://localhost:18000
     */
    private String host = "http://localhost:18000";

}
