package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 创建用户
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "创建用户")
public class CreateUserDTO {

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
    @Size(min = 1, message = "至少要拥有一个角色")
    @ApiModelProperty(value = "角色", required = true, example = "[1,2,3]", notes = "角色ID的集合")
    private List<Long> role;
}
