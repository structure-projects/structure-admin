package cn.structured.admin.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "系统公告")
public class AnnouncementDTO {

    @NotBlank
    @ApiModelProperty(value = "主题", required = true)
    private String subject;

    @NotBlank
    @ApiModelProperty(value = "内容", required = true)
    private String content;

    @ApiModelProperty(value = "作用域: 0 全部，1 app、2 管理端")
    private Integer scope;

    @ApiModelProperty(value = "类型：1长期、2短期")
    private Integer type;

    @ApiModelProperty(value = "是否置顶:0否 1是")
    private Boolean top;

    @ApiModelProperty(value = "失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationTime;

    @ApiModelProperty(value = "状态：1正常，2失效")
    private Integer state;
}
