package cn.structured.sa.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 空间VO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "空间VO")
public class SpaceVO {

    @ApiModelProperty(value = "空间ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "空间名称", example = "公共空间")
    private String name;

    @ApiModelProperty(value = "空间编码", example = "public")
    private String code;

    @ApiModelProperty(value = "空间类型", example = "0", notes = "0 公共空间 1 私有空间")
    private Integer type;

    @ApiModelProperty(value = "是否启用", example = "TRUE")
    private Boolean enabled;

    @ApiModelProperty(value = "操作人", example = "张三")
    private String operator;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "组织ID", example = "1645717015337684992")
    private Long organizationId;

}
