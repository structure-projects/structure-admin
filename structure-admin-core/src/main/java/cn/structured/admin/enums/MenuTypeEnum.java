package cn.structured.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 菜单类型
 *
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Getter
public enum MenuTypeEnum {
    NULL(0, null),
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    EXTLINK(3, "外链"),
    BUTTON(4, "按钮");

    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private final Integer value;

    // @JsonValue //  表示对枚举序列化时返回此字段
    private final String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static MenuTypeEnum getMenuTypeEnum(Integer value) {
        return MenuTypeEnum.values()[value];
    }
}