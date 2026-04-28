package cn.structured.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统全局配置表
 * </p>
 *
 * @author chuck
 * @since 2024-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  主键ID 
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
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 组织ID
     */
    @TableField("organization_id")
    private Long organizationId;


}
