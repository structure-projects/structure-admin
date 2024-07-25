package cn.structured.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索字典类
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索字典类")
public class SearchDictCategoryDto {

    @ApiModelProperty(value = "字典编号", example = "SEX")
    private String code;

    @ApiModelProperty(value = "字典名称", example = "性别字典")
    private String name;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

}
