package cn.structured.admin.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典类VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@Schema(description = "字典类VO")
public class DictCategoryVO {

    @Schema(description = "字典类ID", example = "1645717015337684994")
    private Long id;

    @Schema(description = "字典类名称", example = "性别")
    private String name;

    @Schema(description = "字典类编码", example = "SEX")
    private String code;

    @Schema(description = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

    @Schema(description = "字典类型，系统:1 ,用户:2", example = "1")
    private Integer type;

    @Schema(description = "启用/停用，启用 true,停用 false", example = "true")
    private Boolean enabled;

    @Schema(description = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @Schema(description = "组织ID", example = "1645717015337684992")
    private Long organizationId;
}
