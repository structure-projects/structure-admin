package cn.structured.admin.api.dto;

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

    @ApiModelProperty(value = "当前系统配置KEY", example = "themeColors", required = true)
    private String key;

    @ApiModelProperty(value = "当前系统配置value", example = "#1890FF", required = true)
    private String value;

    @ApiModelProperty(value = "配置描述")
    private String remark;

}
