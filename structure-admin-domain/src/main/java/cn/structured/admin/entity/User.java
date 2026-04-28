package cn.structured.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {

    @TableField("id")
    private Long id;

    @TableField("nick_name")
    private String nickName;
}
