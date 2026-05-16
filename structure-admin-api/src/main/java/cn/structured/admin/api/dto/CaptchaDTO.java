package cn.structured.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "验证码响应DTO")
public class CaptchaDTO {

    @ApiModelProperty(value = "验证码ID")
    private String captchaId;

    @ApiModelProperty(value = "验证码图片Base64")
    private String imageBase64;

    @ApiModelProperty(value = "验证码过期时间（秒）")
    private Long expireSeconds;
}