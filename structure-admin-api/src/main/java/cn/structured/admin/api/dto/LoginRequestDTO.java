package cn.structured.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录请求DTO")
public class LoginRequestDTO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码ID")
    private String captchaId;

    @ApiModelProperty(value = "验证码")
    private String captcha;
}