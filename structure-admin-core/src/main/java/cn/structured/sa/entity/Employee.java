package cn.structured.sa.entity;

import cn.structured.mybatis.plus.starter.annotations.FieldJoin;
import cn.structured.mybatis.plus.starter.annotations.Join;
import cn.structured.mybatis.plus.starter.annotations.JoinCondition;
import cn.structured.mybatis.plus.starter.annotations.LogicDelete;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.sa.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 关联用户ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 工号
     */
    @TableField("emp_no")
    private String empNo;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别,N 未知,M 男 ,F 女
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生日期
     */
    @TableField("birth_date")
    private LocalDate birthDate;

    /**
     * 学历
     */
    @TableField("education")
    private String education;

    /**
     * 健康状况
     */
    @TableField("health")
    private String health;

    /**
     * 婚姻状况
     */
    @TableField("marriage")
    private String marriage;

    /**
     * 民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 政治面貌
     */
    @TableField("politics")
    private String politics;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 户口住址
     */
    @TableField("account_address")
    private String accountAddress;

    /**
     * 居住地址
     */
    @TableField("residence_address")
    private String residenceAddress;

    /**
     * 紧急联系人
     */
    @TableField("emergency_contact")
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    @TableField("emergency_contact_phone")
    private String emergencyContactPhone;

    /**
     * 部门id
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 部门层级
     */
    @TableField("dept")
    private String dept;

    /**
     * 职级  字典类编码 POSITION_LEVEL 系统级别
     */
    @TableField("position_level")
    private String positionLevel;

    /**
     * 职位  字典类编码 POSITION 系统级别
     */
    @TableField("position")
    private String position;

    /**
     * 职工状态
     */
    @TableField("state")
    private String state;

    /**
     * 是否删除 0 否 ，1 是
     */
    @LogicDelete
    @TableLogic
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    @FieldJoin(type = JoinResultEnum.ONE, result = String.class, value = {
            @Join(group = {SearchGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"nick_name"},
                    value = {@JoinCondition(currentColumn = "update_by", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private String operator;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 组织ID
     */
    @TableField(value = "organization_id", fill = FieldFill.INSERT)
    private Long organizationId;

    /**
     * 职务 字典类编码 DUTY 系统级别
     */
    @TableField("duty")
    private String duty;

    @FieldJoin(type = JoinResultEnum.ONE, result = User.class, value = {
            @Join(result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"id", "username"},
                    value = {@JoinCondition(currentColumn = "user_id", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private User user;
}
