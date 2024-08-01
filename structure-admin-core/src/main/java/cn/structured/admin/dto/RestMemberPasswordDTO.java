package cn.structured.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 重置成员密码DTO
 *
 * @author chuck
 * @version 2024/07/12 上午4:09
 * @since 1.8
 */
@Data
@ApiModel(value = "重置成员密码DTO")
public class RestMemberPasswordDTO {

    @NotNull
    @ApiModelProperty(value = "成员ID")
    private Long memberId;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;
}
