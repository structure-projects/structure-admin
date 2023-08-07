package cn.structured.sa.entity;

import cn.structured.mybatis.plus.starter.annotations.*;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.sa.group.DetailsGroup;
import cn.structured.sa.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    @Where(value = {
            @Condition(group = SearchGroup.class, sqlCondition = SqlCondition.LIKE)
    })
    private String username;

    /**
     * 域用户名
     */
    @TableField("domain_username")
    private String domainUsername;

    /**
     * 加密后的密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @Where(value = {
            @Condition(group = SearchGroup.class, sqlCondition = SqlCondition.LIKE)
    })
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像
     */

    @TableField("photo")
    private String photo;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @Where(value = {
            @Condition(group = SearchGroup.class, sqlCondition = SqlCondition.LIKE)
    })
    @TableField("phone")
    private String phone;

    /**
     * 是否启用 1:  启用 0:未启用
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

    /**
     * 是否未锁定 0:  锁定 1:未锁定
     */
    @TableField("is_unlocked")
    private Boolean unlocked;

    /**
     * 是否删除 0：未删除 1：删除
     */
    @LogicDelete
    @TableField("is_deleted")
    @TableLogic
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
            @Join(group = {SearchGroup.class,DetailsGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
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
     * <p>
     * 角色映射
     * </p>
     * <pre>
     *     这里主要用于列表和详情时支持这个角色的映射,此处的映射配置相当于在在源的sql上拼接 LEFT JOIN
     *     sourceSql = SELECT * FROM user
     *     targetSql = SELECT user.* ,r.id,r.name,r.code FROM user user LEFT JOIN user_role ur ON user.id = ur.user_id LEFT JOIN role r ON r.id = ur.role_id
     *     //不建议默认分页使用链表查询会影响行数和返回结果有分页场景请勿直接使用分页插件,可手动处理分页使用
     * </pre>
     */
    @TableField(exist = false)
    @FieldJoin(type = JoinResultEnum.MANY, result = Role.class, value = {
            @Join(group = {DetailsGroup.class}, joinType = JoinTypeEnum.LEFT_JOIN, joinTarget = UserRole.class, aliasName = "ur", value = {
                    @JoinCondition(currentColumn = "id", targetColumn = "user_id")
            }),
            @Join(group = {DetailsGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN, joinTarget = Role.class, aliasName = "r", columns = {"id", "name", "code"}, value = {
                    @JoinCondition(condition = "ur.role_id = r.id")
            })
    })
    private List<Role> roleList;

}
