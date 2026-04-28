package cn.structured.admin.entity;

import cn.structured.mybatis.plus.starter.annotations.LogicDelete;
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
 * @since 2024-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 功能编号
     */
    @TableField("code")
    private String code;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单类型(1：菜单；2：目录；3：外链；4：按钮)
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    @TableField("component")
    private String component;

    /**
     * 按钮权限标识
     */
    @TableField("perm")
    private String perm;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 状态(0:禁用;1:开启)
     */
    @TableField("visible")
    private Boolean visible;

    /**
     * 跳转路径
     */
    @TableField("redirect")
    private String redirect;

    @TableField("tree_path")
    private String treePath;

    /**
     * 【目录】只有一个子路由是否始终显示(1:是 0:否)
     */
    @TableField("always_show")
    private Boolean alwaysShow;

    /**
     * 【菜单】是否开启页面缓存(1:是 0:否)
     */
    @TableField("keep_alive")
    private Boolean keepAlive;

    /**
     * 是否启用 0 否 ，1 是
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

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


}
