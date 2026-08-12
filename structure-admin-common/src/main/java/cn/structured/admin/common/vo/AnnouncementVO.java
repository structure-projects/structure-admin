package cn.structured.admin.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统公告VO
 *
 * @author chuck
 * @version 1.0
 * @since 1.8
 * @since 2026/1/1-上午4:29
 */
@Data
@Schema(description = "系统公告VO")
public class AnnouncementVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "主题")
    private String subject;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "作用域: 1 app、2 管理端")
    private Integer scope;

    @Schema(description = "类型：1长期、2短期")
    private Integer type;

    @Schema(description = "是否置顶:0否 1是")
    private Boolean top;

    @Schema(description = "失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expirationTime;

    @Schema(description = "状态：1正常，2失效")
    private Integer state;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
