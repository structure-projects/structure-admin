package cn.structured.sa.client.dto.dict;

import cn.structured.sa.client.groups.Create;
import cn.structured.sa.client.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典项DTO
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Data
@ApiModel(description = "字典项DTO")
public class DictItemDTO {

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "字典项ID", example = "1645717015337684994")
    private Long id;

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项名称", example = "性别")
    private String name;

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项编码", example = "SEX")
    private String code;

    @NotBlank(groups = Create.class)
    @ApiModelProperty(value = "字典项数值", example = "N")
    private String value;
}
