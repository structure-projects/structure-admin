package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 更新用户
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新用户")
public class UpdateUserDTO {

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "1645717015337684992", required = true)
    private Long id;

    @ApiModelProperty(value = "昵称", example = "韦小宝")
    private String nickName;

    @ApiModelProperty(value = "头像", example = "https://structured-doc.oss-cn-beijing.aliyuncs.com/java/img/01.png")
    private String photo;

    @ApiModelProperty(value = "电子邮箱", example = "16866668888@163.com")
    private String email;

    @ApiModelProperty(value = "电话号码", example = "16866668888")
    private String phone;

    @Size(min = 1, message = "至少要拥有一个角色")
    @ApiModelProperty(value = "角色", required = true, example = "[1,2,3]", notes = "角色ID的集合")
    private List<Long> role;
}
