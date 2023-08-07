package cn.structured.sa.client.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 更新职工
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新职工")
public class UpdateEmployeeDTO {

    @NotNull
    @ApiModelProperty(value = "职工ID", example = "1645717015337684992", notes = "等同于用户ID")
    private Long id;

    @ApiModelProperty(value = "职工编号", example = "100001", notes = "工号")
    private String empNo;

    @ApiModelProperty(value = "职工姓名", example = "张三", notes = "职工姓名和用户昵称会进行同步！")
    private String name;

    @ApiModelProperty(value = "性别", example = "F", notes = "N 未知,M 男 ,F 女！")
    private String sex;

    @ApiModelProperty(value = "出生日期", example = "1995-08-05", notes = "用户的出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private LocalDate birthDate;

    @ApiModelProperty(value = "学历", example = "DZ", notes = "学历存储的是字典表里的字典项，字典类编码为：EDUCATION")
    private String education;

    @ApiModelProperty(value = "健康状况", example = "JK", notes = "健康状况存储的是字典表里的字典项，字典类编码为：HEALTH")
    private String health;

    @ApiModelProperty(value = "婚姻状况", example = "WH", notes = "婚姻状况存储的是字典表里的字典项，字典类编码为：MARRIAGE")
    private String marriage;

    @ApiModelProperty(value = "民族", example = "MZ", notes = "民族存储的是字典表里的字典项，字典类编码为：NATION")
    private String nation;

    @ApiModelProperty(value = "政治面貌", example = "QZ", notes = "政治面貌存储的是字典表里的字典项，字典类编码为：POLITICS")
    private String politics;

    @ApiModelProperty(value = "手机号", example = "16888886666", notes = "手机号会于用户表中的手机号同步")
    private String phone;

    @ApiModelProperty(value = "身份证号", example = "210xxx19950806xxxx")
    private String idCard;

    @ApiModelProperty(value = "户口住址", example = "辽宁省北京市XXXXX")
    private String accountAddress;

    @ApiModelProperty(value = "居住地址", example = "北京市XXXXX")
    private String residenceAddress;

    @ApiModelProperty(value = "紧急联系人", example = "张老大")
    private String emergencyContact;

    @ApiModelProperty(value = "紧急联系人电话", example = "16888888888")
    private String emergencyContactPhone;

    @ApiModelProperty(value = "部门id", example = "[1645717015337684992,1645717015337684992]")
    private String dept;

    @ApiModelProperty(value = "职务", example = "Zy", notes = "存储的是字典表里的字典项字典类编码为：DUTY")
    private String duty;

    @ApiModelProperty(value = "职级", example = "BZ", notes = "存储的是字典表里的字典项字典类编码为：POSITION_LEVEL")
    private String positionLevel;

    @ApiModelProperty(value = "职位", example = "BZ", notes = "存储的是字典表里的字典项字典类编码为：POSITION")
    private String position;

    @ApiModelProperty(value = "职工状态", example = "1", notes = "存储的是字典表里的字典项字典类编码为：EMP_STATE")
    private String state;

}
