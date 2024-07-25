package cn.structured.admin.entity;

import cn.structured.mybatis.plus.starter.annotations.LogicDelete;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 成员表
 * </p>
 *
 * @author chuck
 * @since 2024-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成员ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 成员手机号
     */
    @TableField("phone")
    private String phone;

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
     * 部门id
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 成员状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 是否删除 0 否 ，1 是
     */
    @LogicDelete
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
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
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 组织ID
     */
    @TableField("organization_id")
    private Long organizationId;

    @TableField(exist = false)
    private List<Long> roleIds;

}
