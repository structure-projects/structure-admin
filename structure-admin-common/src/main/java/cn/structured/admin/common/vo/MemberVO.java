package cn.structured.admin.common.vo;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "成员 - VO")
public class MemberVO {

    @Schema(description = "成员ID")
    private Long mid;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "成员手机号")
    private String phone;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别,N 未知,M 男 ,F 女")
    private String sex;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "部门名")
    private String deptName;

    @Schema(description = "成员状态")
    private Integer state;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
