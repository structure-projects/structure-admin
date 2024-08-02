package cn.structured.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 当前系统配置value
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "当前系统配置value - VO")
public class ConfigVO {

    @ApiModelProperty(value = "配置ID")
    private Long id;

    @ApiModelProperty(value = "当前系统配置KEY", example = "themeColors", required = true)
    private String key;

    @ApiModelProperty(value = "当前系统配置value", example = "#1890FF", required = true)
    private String value;

    @ApiModelProperty(value = "配置描述")
    private String remark;

}
