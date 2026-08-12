package cn.structured.admin.common.dto;

import cn.structured.admin.common.groups.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 字典项DTO
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Data
@Schema(description = "字典项DTO")
public class DictItemDTO {

    @NotBlank(groups = Create.class)
    @Schema(description = "字典项名称", example = "性别")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @NotBlank(groups = Create.class)
    @Schema(description = "字典项编码", example = "SEX")
    private String code;

    @Schema(description = "是否启用")
    private Boolean enabled;

    @NotBlank(groups = Create.class)
    @Schema(description = "字典项数值", example = "N")
    private String value;
}
