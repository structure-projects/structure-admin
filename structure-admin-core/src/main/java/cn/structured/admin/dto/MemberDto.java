package cn.structured.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "成员 - DTO")
public class MemberDto {

    @ApiModelProperty(value = "成员手机号")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别,N 未知,M 男 ,F 女")
    private String sex;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "角色ID")
    private List<Long> roleIds;

    @ApiModelProperty(value = "成员状态")
    private Integer state;
}
