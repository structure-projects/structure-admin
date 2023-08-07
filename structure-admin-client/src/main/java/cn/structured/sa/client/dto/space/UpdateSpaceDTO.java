package cn.structured.sa.client.dto.space;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 更新空间DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "更新空间")
public class UpdateSpaceDTO {
    @ApiModelProperty(value = "空间ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "空间名称", example = "公共空间")
    private String name;

    @ApiModelProperty(value = "空间编码", example = "public")
    private String code;

    @ApiModelProperty(value = "空间类型", example = "0", notes = "0 公共空间 1 私有空间")
    private Integer type;
}
