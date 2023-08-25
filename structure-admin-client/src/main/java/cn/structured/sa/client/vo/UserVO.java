package cn.structured.sa.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
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
public class UserVO {
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

    @ApiModelProperty(value = "是否启用", example = "true")
    private Boolean enabled;

    @ApiModelProperty(value = "是否锁定", example = "false")
    private Boolean locked;

    @ApiModelProperty(value = "操作人", example = "张三")
    private String operator;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "角色")
    private List<Long> role;
}
