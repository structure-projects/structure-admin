package cn.structured.admin.api.aop;

import java.lang.annotation.*;

/**
 *  操作日志注解 ，
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    String value() default "";

    String module() default "";
}
