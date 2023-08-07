package cn.structured.sa.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 职工VO
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "职工VO")
public class EmployeeVO {

    @ApiModelProperty(value = "职工ID", example = "1645717015337684992", notes = "等同于用户ID")
    private Long id;

    @ApiModelProperty(value = "职工编号", example = "100001", notes = "工号")
    private String empNo;

    @ApiModelProperty(value = "职工姓名", example = "张三", notes = "职工姓名和用户昵称会进行同步！")
    private String name;

    @ApiModelProperty(value = "性别", example = "F", notes = "N 未知,M 男 ,F 女！")
    private String sex;

    @ApiModelProperty(value = "手机号", example = "16888886666", notes = "手机号会于用户表中的手机号同步")
    private String phone;

    @ApiModelProperty(value = "部门id", example = "1645717015337684992,1645717015337684992")
    private String dept;

    @ApiModelProperty(value = "职工状态", example = "1", notes = "存储的是字典表里的字典项字典类编码为：EMP_STATE")
    private String state;

    @ApiModelProperty(value = "组织ID", example = "1645717015337684992")
    private Long organizationId;

    @ApiModelProperty(value = "用户ID", example = "1645717015337684992")
    private Long userId;

    @ApiModelProperty(value = "操作人", example = "张三")
    private String operator;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

}
