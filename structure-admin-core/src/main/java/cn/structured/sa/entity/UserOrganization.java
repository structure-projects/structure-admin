package cn.structured.sa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户组织表
 * </p>
 *
 * @author chuck
 * @since 2023-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_organization")
public class UserOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableField("id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 组织ID
     */
    @TableField("organization_id")
    private Long organizationId;

    /**
     * 域账号
     */
    @TableField("domain_username")
    private String domainUsername;

    /**
     * 是否默认组织：0 否 1 是
     */
    @TableField("is_default_organization")
    private Boolean defaultOrganization;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
