package cn.structured.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "系统公告VO")
public class AnnouncementVO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "主题")
    private String subject;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "作用域: 1 app、2 管理端")
    private Integer scope;

    @ApiModelProperty(value = "类型：1长期、2短期")
    private Integer type;

    @ApiModelProperty(value = "是否置顶:0否 1是")
    private Boolean top;

    @ApiModelProperty(value = "失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expirationTime;

    @ApiModelProperty(value = "状态：1正常，2失效")
    private Integer state;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
