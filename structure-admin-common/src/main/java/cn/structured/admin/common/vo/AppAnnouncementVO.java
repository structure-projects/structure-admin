package cn.structured.admin.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "应用上展示的系统公告")
public class AppAnnouncementVO {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "主题")
    private String subject;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
