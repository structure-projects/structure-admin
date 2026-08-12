package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 创建字典类
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@Schema(description = "字典类-DTO")
public class DictCategoryDTO {

    @NotBlank
    @Schema(description = "字典类名称", example = "性别")
    private String name;

    @NotBlank
    @Schema(description = "字典类编码", example = "SEX")
    private String code;

    @Schema(description = "是否启用")
    private Boolean enabled;

    @Schema(description = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

}
