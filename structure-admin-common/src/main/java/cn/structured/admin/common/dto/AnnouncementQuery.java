package cn.structured.admin.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "公告查询参数")
public class AnnouncementQuery {

    @Schema(description = "作用域: 0 全部，1 app、2 管理端")
    private Integer scope;

    @Schema(description = "类型：1长期、2短期")
    private Integer type;

    @Schema(description = "是否置顶:0否 1是")
    private Boolean top;

    @Schema(description = "状态：1正常，2失效")
    private Integer state;


    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


}
