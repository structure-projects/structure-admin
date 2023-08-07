package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 更改个人信息指令
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更改个人信息")
public class ChangeProfileDTO {

    @ApiModelProperty(value = "昵称", example = "韦小宝")
    private String nickName;

    @ApiModelProperty(value = "头像", example = "https://structured-doc.oss-cn-beijing.aliyuncs.com/java/img/01.png")
    private String photo;

    @ApiModelProperty(value = "电子邮箱", example = "16866668888@163.com")
    private String email;

    @ApiModelProperty(value = "电话号码", example = "16866668888")
    private String phone;
}
