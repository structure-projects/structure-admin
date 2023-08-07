package cn.structured.sa.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author chuck
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 配置键
     */
    @TableField("code")
    private String code;

    /**
     * 配置值
     */
    @TableField("value")
    private String value;

    /**
     * 组织ID
     */
    @TableField(value = "organization_id", fill = FieldFill.INSERT)
    private Long organizationId;


    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * type 配置类型 0 系统 , 1 用户
     */
    @TableField(value = "type")
    private Integer type;


}
