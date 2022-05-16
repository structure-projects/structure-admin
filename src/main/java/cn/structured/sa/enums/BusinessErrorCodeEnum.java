package cn.structured.sa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 业务错误码
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2022/5/17 0:12
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorCodeEnum {

    LOGIN_ERROR("LOGIN_ERR", "username or password error !");

    private String code;

    private String message;
}
