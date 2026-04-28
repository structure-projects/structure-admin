package cn.structured.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统公告
 * </p>
 *
 * @author chuck
 * @since 2026-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("announcement")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主题
     */
    @TableField("subject")
    private String subject;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作用域: 1 app、2 管理端
     */
    @TableField("scope")
    private Integer scope;

    /**
     * 类型：1长期、2短期
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否置顶:0否 1是
     */
    @TableField("is_top")
    private Boolean top;

    /**
     * 失效时间
     */
    @TableField("expiration_time")
    private LocalDateTime expirationTime;

    /**
     * 状态：1正常，2失效
     */
    @TableField("state")
    private Integer state;

    /**
     * 租户ID
     */
    @TableField("organization_id")
    private Long tenantId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 逻辑删除 0-否 1-是
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;


}
