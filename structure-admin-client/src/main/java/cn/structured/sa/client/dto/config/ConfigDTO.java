package cn.structured.sa.client.dto.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 配置-DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "配置-DTO")
public class ConfigDTO {

    @ApiModelProperty(value = "当前用户系统配置KEY", example = "themeColors" ,required = true)
    private String key;

    @ApiModelProperty(value = "当前用户系统配置value", example = "#1890FF",required = true)
    private String value;

}
