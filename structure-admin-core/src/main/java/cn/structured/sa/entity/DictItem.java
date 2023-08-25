package cn.structured.sa.entity;

import cn.structured.mybatis.plus.starter.annotations.FieldJoin;
import cn.structured.mybatis.plus.starter.annotations.Join;
import cn.structured.mybatis.plus.starter.annotations.JoinCondition;
import cn.structured.mybatis.plus.starter.annotations.LogicDelete;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.sa.group.ListGroup;
import cn.structured.sa.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典项
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dict_item")
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 值
     */
    @TableField("value")
    private String value;

    /**
     * 顺序
     */
    @TableField("sequence")
    private Integer sequence;

    /**
     * 是否使用过的（不允许删除只能够禁用）
     */
    @TableField("is_used")
    private Boolean used;

    /**
     * 是否启用 0 否 ，1 是
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

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
            @Join(group = {ListGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
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


}
