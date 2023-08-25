package cn.structured.sa.entity;

import cn.structured.mybatis.plus.starter.annotations.*;
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
 * 部门表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 父id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 父ids
     */
    @TableField("pids")
    private String pids;

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
     * 顺序
     */
    @TableField("sequence")
    private Integer sequence;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;

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
            @Join(group = {SearchGroup.class, ListGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"nick_name"},
                    value = {@JoinCondition(currentColumn = "update_by", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private String operator;

    /**
     * 查询是否有子集 改写法表示我只希望查询出一个子部门就可以，因为有分页不期望查询太多结果会影响分页行数
     * targetSql : SELECT * FROM dept LEFT JOIN dept d ON d.id = (SELECT id FROM dept t WHERE t.pid = dept.id AND t.is_deleted = 0 limit 1)
     */
    @FieldJoin(type = JoinResultEnum.ONE, result = Dept.class, value = {
            @Join(group = {SearchGroup.class, ListGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = Dept.class, aliasName = "d",
                    columns = {"id", "name"},
                    value = {@JoinCondition(condition = "d.id = (SELECT id FROM dept t WHERE t.pid = dept.id AND t.is_deleted=0 limit 1)")}),
    })
    @TableField(exist = false)
    private Dept children;

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
