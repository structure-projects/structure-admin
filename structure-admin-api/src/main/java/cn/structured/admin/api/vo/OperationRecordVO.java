package cn.structured.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "操作日志VO")
public class OperationRecordVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 成员ID
     */
    @ApiModelProperty(value = "成员ID")
    private Long mid;

    /**
     * 操作行为
     */
    @ApiModelProperty(value = "操作行为")
    private String action;

    /**
     * 操作模块
     */
    @ApiModelProperty(value = "操作模块")
    private Integer module;


    /**
     * 操作参数
     */
    @ApiModelProperty(value = "操作参数")
    private String operationParams;

    /**
     * 操作结果
     */
    @ApiModelProperty(value = "操作结果")
    private String operationResult;

    /**
     * 操作状态：1成功 0失败
     */
    @ApiModelProperty(value = "操作状态：1成功 0失败")
    private Integer status;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    private String ipAddress;

    /**
     * 用户代理
     */
    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    /**
     * 耗时(毫秒)
     */
    @ApiModelProperty(value = "耗时(毫秒)")
    private Long costTime;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "操作人")
    private String operationUser;

}
