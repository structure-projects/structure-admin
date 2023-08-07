package cn.structured.sa.client.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索用户
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索用户")
public class SearchUserDTO {

    @ApiModelProperty(value = "用户名", example = "zhangsan")
    private String username;

    @ApiModelProperty(value = "昵称", example = "韦小宝")
    private String nickName;

    @ApiModelProperty(value = "电话号码", example = "16866668888")
    private String phone;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

    @ApiModelProperty(value = "是否锁定", example = "false", notes = "锁定 true,未锁定 false")
    private Boolean locked;

}
