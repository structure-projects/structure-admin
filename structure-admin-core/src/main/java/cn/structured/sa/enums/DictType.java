package cn.structured.sa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典类型
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
@Getter
@AllArgsConstructor
public enum DictType {

    /**
     * 字典类型
     */
    SYS(1, "系统类型"),
    USER(2, "用户类型");

    /**
     * 类型值
     */
    private Integer value;

    /**
     * 类型名
     */
    private String title;
}
