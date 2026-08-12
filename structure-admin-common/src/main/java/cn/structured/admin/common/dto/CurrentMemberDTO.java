package cn.structured.admin.common.dto;

import cn.structured.user.common.dto.user.UserDetailDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当前成员
 * 
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Data
@Schema(description = "当前成员 - Dto")
public class CurrentMemberDTO {

    @Schema(description = "成员ID")
    private Long memberId;

    @Schema(description = "部门信息")
    private String deptInfo;

    @Schema(description = "当前用户详情")
    private UserDetailDTO userDetail;

}
