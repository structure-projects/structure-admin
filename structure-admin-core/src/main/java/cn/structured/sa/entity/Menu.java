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
 * 功能菜单表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
public class Menu implements Serializable {

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
    private String name;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 菜单类型（0目录 1菜单 2按钮 3 功能）
     */
    @TableField("type")
    private Integer type;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 路由地址
     */
    @TableField("router")
    private String router;

    /**
     * 组件地址
     */
    @TableField("component")
    private String component;

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    /**
     * 打开方式（字典 0无 1组件 2内链 3外链）
     */
    @TableField("open_type")
    private Integer openType;

    /**
     * 是否可见（0-否,1-是）
     */
    @TableField("is_visible")
    private Boolean visible;

    /**
     * 链接地址
     */
    @TableField("link")
    private String link;

    /**
     * 重定向地址
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 菜单类型（1系统权重 2业务权重）
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 权重顺序
     */
    @TableField("sequence")
    private Integer sequence;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;

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
            @Join(group = {SearchGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"nick_name"},
                    value = {@JoinCondition(currentColumn = "update_by", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private String operator;

    @FieldJoin(type = JoinResultEnum.ONE, result = Menu.class, value = {
            @Join(group = {SearchGroup.class, ListGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = Menu.class, aliasName = "d",
                    columns = {"id", "name"},
                    value = {@JoinCondition(condition = "d.id = (SELECT id FROM menu t WHERE t.pid = menu.id AND t.is_deleted=0 limit 1)")}),
    })
    @TableField(exist = false)
    private Menu children;

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
