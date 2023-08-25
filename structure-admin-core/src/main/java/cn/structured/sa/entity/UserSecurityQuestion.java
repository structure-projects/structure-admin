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
 * 用户密保问题
 * </p>
 *
 * @author chuck
 * @since 2023-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_security_question")
public class UserSecurityQuestion implements Serializable {

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
     * 问题
     */
    @TableField("question")
    private String question;

    /**
     * 答案
     */
    @TableField("answer")
    private String answer;

    /**
     * 密保提示信息
     */
    @TableField("hint")
    private String hint;

    /**
     * 密保手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 密保类型： 0 手机，1 邮箱 ，3 问答
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否使用，0 否 ，1 是
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

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


}
