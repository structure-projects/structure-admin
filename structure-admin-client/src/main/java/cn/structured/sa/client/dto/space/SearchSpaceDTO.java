package cn.structured.sa.client.dto.space;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检索空间DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "搜索空间")
public class SearchSpaceDTO {

    @ApiModelProperty(value = "空间名称", example = "公共空间")
    private String name;

    @ApiModelProperty(value = "空间编码", example = "public")
    private String code;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;
}
