package cn.structured.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建字典类
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "字典类-DTO")
public class DictCategoryDto {

    @NotBlank
    @ApiModelProperty(value = "字典类名称", example = "性别")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "字典类编码", example = "SEX")
    private String code;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

}
