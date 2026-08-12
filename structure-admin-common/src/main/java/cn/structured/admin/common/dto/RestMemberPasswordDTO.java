package cn.structured.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 重置成员密码DTO
 *
 * @author chuck
 * @version 2024/07/12 上午4:09
 * @since 1.8
 */
@Data
@Schema(description = "重置成员密码DTO")
public class RestMemberPasswordDTO {

    @NotNull
    @Schema(description = "成员ID")
    private Long memberId;

    @NotBlank
    @Schema(description = "密码")
    private String password;
}
