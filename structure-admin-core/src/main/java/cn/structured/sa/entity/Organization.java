package cn.structured.sa.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 组织机构表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 机构名称
     */
    @TableField("name")
    private String name;

    /**
     * 机构地址
     */
    @TableField("address")
    private String address;

    /**
     * 域账号
     */
    @TableField("domain")
    private String domain;

    /**
     * 机构介绍
     */
    @TableField("introduce")
    private String introduce;

    /**
     * 管理员名称
     */
    @TableField("admin_name")
    private String adminName;

    /**
     * 管理员电话
     */
    @TableField("admin_phone")
    private String adminPhone;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 平台组织ID
     */
    @TableField(value = "organization_id", fill = FieldFill.INSERT)
    private Long organizationId;

}
