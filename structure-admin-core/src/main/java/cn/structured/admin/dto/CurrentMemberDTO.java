package cn.structured.admin.dto;

import cn.structured.user.api.dto.user.UserDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 当前成员
 * 
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Data
@ApiModel(value = "当前成员 - Dto")
public class CurrentMemberDTO {

    @ApiModelProperty(value = "成员ID")
    private Long memberId;

    @ApiModelProperty(value = "部门信息")
    private String deptInfo;

    @ApiModelProperty(value = "当前用户详情")
    private UserDetailDTO userDetail;

}
