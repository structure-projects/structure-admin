package cn.structured.admin.api.dto;


import lombok.Data;

/**
 * 操作日志
 *
 * @author chuck
 * @since 2026/1/2
 */
@Data
public class OperationDTO {

    /**
     * 成员ID
     */
    private Long mid;

    /**
     * 操作行为
     */
    private String action;

    /**
     * 操作模块
     */
    private Integer module;

    /**
     * 积分变动
     */
    private Long score;

    /**
     * 操作参数
     */
    private String operationParams;

    /**
     * 操作结果
     */
    private String operationResult;

    /**
     * 操作状态：1成功 0失败
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 操作IP
     */
    private String ipAddress;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 耗时(毫秒)
     */
    private Long costTime;
}
