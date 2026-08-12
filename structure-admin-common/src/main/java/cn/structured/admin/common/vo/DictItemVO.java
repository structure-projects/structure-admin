package cn.structured.admin.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典项VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@Schema(description = "字典项VO")
public class DictItemVO {

    @Schema(description = "字典项ID", example = "1645717015337684994")
    private Long id;

    @Schema(description = "字典项名称", example = "性别")
    private String name;

    @Schema(description = "字典项编码", example = "SEX")
    private String code;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "字典项数值", example = "N")
    private String value;

    @Schema(description = "启用/停用，启用 true,停用 false", example = "true")
    private Boolean enabled;

    @Schema(description = "操作人", example = "张三")
    private String operator;

    @Schema(description = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

}
