package cn.structured.sa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户绑定表
 * </p>
 *
 * @author chuck
 * @since 2023-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_bind")
public class UserBind implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 平台账号
     */
    @TableField("platform_account")
    private String platformAccount;

    /**
     * 平台类型
     */
    @TableField("platform_code")
    private String platformCode;

    /**
     * 状态:0 绑定，1 解绑
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间（绑定时间）
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
