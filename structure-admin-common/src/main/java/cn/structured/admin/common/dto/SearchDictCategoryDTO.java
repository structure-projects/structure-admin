package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 搜索字典类
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@Schema(description = "搜索字典类")
public class SearchDictCategoryDTO {

    @Schema(description = "字典编号", example = "SEX")
    private String code;

    @Schema(description = "字典名称", example = "性别字典")
    private String name;

    @Schema(description = "启用/停用，启用 true,停用 false", example = "true")
    private Boolean enabled;

}
