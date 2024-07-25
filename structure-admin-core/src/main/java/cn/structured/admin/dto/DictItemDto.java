package cn.structured.admin.dto;

import cn.structured.basic.api.groups.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典项DTO
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Data
@ApiModel(description = "字典项DTO")
public class DictItemDto {

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项名称", example = "性别")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项编码", example = "SEX")
    private String code;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项数值", example = "N")
    private String value;
}
