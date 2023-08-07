package cn.structured.sa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 空间部门
 * </p>
 *
 * @author chuck
 * @since 2023-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("space_dept")
public class SpaceDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 空间ID
     */
    @TableField("space_id")
    private Long spaceId;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Long deptId;


}
