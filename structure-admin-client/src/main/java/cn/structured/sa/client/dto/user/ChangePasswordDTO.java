package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 更改用户密码
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更改用户密码")
public class ChangePasswordDTO {

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "1645717015337684992", required = true)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "旧的密码", required = true, example = "e83cd61486f784abd64630f18eafcf2c", notes = "将明文生成MD5后传输")
    private String oldPassword;

    @NotBlank
    @ApiModelProperty(value = "新的密码", required = true, example = "e83cd61486f784abd64630f18eafcf2e", notes = "将明文生成MD5后传输")
    private String newPassword;

}
