package cn.structured.sa.client.dto.space;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建空间DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "搜索空间")
public class CreateSpaceDTO {

    @ApiModelProperty(value = "空间名称", example = "公共空间")
    private String name;

    @ApiModelProperty(value = "空间编码", example = "public")
    private String code;

    @ApiModelProperty(value = "空间类型", example = "0", notes = "0 公共空间 1 私有空间")
    private Integer type;
}
