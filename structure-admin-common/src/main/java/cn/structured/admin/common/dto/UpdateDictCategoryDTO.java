package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 更新字典类
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@Schema(description = "更新字典类")
public class UpdateDictCategoryDTO {

    @Schema(description = "字典类名称", example = "性别")
    private String name;

    @Schema(description = "字典类编码", example = "SEX")
    private String code;

    @Schema(description = "字符上限", example = "100")
    private Integer upperLimit;

    @Schema(description = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

    @Schema(description = "权重顺序，字典类的排序", example = "0")
    private Integer sequence;
}
