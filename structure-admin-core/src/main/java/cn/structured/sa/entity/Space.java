package cn.structured.sa.entity;

import cn.structured.mybatis.plus.starter.annotations.*;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.sa.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 空间表
 * </p>
 *
 * @author chuck
 * @since 2023-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("space")
public class Space implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    @Where(value = {
            @Condition(group = SearchGroup.class, sqlCondition = SqlCondition.LIKE)
    })
    private String name;

    /**
     * 编码
     */
    @TableField("code")
    @Where(value = {
            @Condition(group = SearchGroup.class, sqlCondition = SqlCondition.LIKE)
    })
    private String code;

    /**
     * 空间类型 0 公共空间 1 私有空间
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否启用 0 否 ，1 是
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

    /**
     * 是否删除 0 否 ，1 是
     */
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

}
