package cn.structured.sa.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户VO
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "用户VO")
public class CurrentUserVO {
    @ApiModelProperty(value = "用户ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String photo;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String phone;
    //todo 嵌套
    @ApiModelProperty(value = "角色")
    private List<OptionVO> roles;

    @ApiModelProperty(value = "部门")
    private String deptId;

    @ApiModelProperty(value = "职务")
    private String duty;
}
