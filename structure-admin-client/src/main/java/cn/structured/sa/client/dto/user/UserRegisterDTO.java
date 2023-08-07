package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户注册DTO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(value = "用户注册")
public class UserRegisterDTO {

    @NotBlank
    @ApiModelProperty(value = "用户名", example = "weixiaobao")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码", required = true, example = "e83cd61486f784abd64630f18eafcf2c", notes = "将明文生成MD5后传输")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "昵称", required = true, example = "韦小宝")
    private String nickName;

    @NotBlank
    @ApiModelProperty(value = "头像", required = true, example = "https://structured-doc.oss-cn-beijing.aliyuncs.com/java/img/01.png")
    private String photo;

    @NotBlank
    @ApiModelProperty(value = "电子邮箱", required = true, example = "16866668888@163.com")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "电话号码", required = true, example = "16866668888")
    private String phone;

    @NotNull
    @ApiModelProperty(value = "组织ID", required = true, example = "1645717015337684990")
    private Long organizationId;
}
