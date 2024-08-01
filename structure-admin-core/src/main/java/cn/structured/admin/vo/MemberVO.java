package cn.structured.admin.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 成员-VO
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(value = "成员 - VO")
public class MemberVO {

    @ApiModelProperty(value = "成员ID")
    private Long id;

    @ApiModelProperty(value = "成员手机号")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别,N 未知,M 男 ,F 女")
    private String sex;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门名")
    private String deptName;

    @ApiModelProperty(value = "角色ID")
    private List<Long> roleIds;

    @ApiModelProperty(value = "成员状态")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
