package cn.structured.sa.client.dto.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建字典类
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "创建字典类")
public class CreateDictCategoryDTO {

    @NotBlank
    @ApiModelProperty(value = "字典类名称", example = "性别")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "字典类编码", example = "SEX")
    private String code;

    @ApiModelProperty(value = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

    @NotNull
    @ApiModelProperty(value = "字符上限", example = "100")
    private Integer upperLimit;

    @ApiModelProperty(value = "权重顺序", example = "0", notes = "字典类的排序")
    private Integer sequence;

}
