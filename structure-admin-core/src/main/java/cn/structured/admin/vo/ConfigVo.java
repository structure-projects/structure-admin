package cn.structured.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "当前系统配置value - VO")
public class ConfigVo {

    @ApiModelProperty(value = "配置ID")
    private Long id;

    @ApiModelProperty(value = "当前系统配置KEY", example = "themeColors", required = true)
    private String key;

    @ApiModelProperty(value = "当前系统配置value", example = "#1890FF", required = true)
    private String value;

    @ApiModelProperty(value = "配置描述")
    private String remark;

}
