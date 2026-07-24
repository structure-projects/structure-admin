package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
 * 成员 - DTO
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Data
@Schema(description = "成员 - DTO")
public class MemberDto {

    @Schema(description = "成员手机号")
    private String phone;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别,N 未知,M 男 ,F 女")
    private String sex;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "角色ID")
    private List<Long> roleIds;

    @Schema(description = "成员状态")
    private Integer state;
}
