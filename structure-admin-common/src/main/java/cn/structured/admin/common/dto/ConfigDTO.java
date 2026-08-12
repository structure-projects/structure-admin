package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 配置-DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@Schema(description = "配置-DTO")
public class ConfigDTO {

    @Schema(description = "当前系统配置KEY", example = "themeColors", requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;

    @Schema(description = "当前系统配置value", example = "#1890FF", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @Schema(description = "配置描述")
    private String remark;

}
