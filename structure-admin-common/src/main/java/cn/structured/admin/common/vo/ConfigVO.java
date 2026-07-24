package cn.structured.admin.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当前系统配置value
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@Schema(description = "当前系统配置value - VO")
public class ConfigVO {

    @Schema(description = "配置ID")
    private Long id;

    @Schema(description = "当前系统配置KEY", example = "themeColors", requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;

    @Schema(description = "当前系统配置value", example = "#1890FF", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @Schema(description = "配置描述")
    private String remark;

}
