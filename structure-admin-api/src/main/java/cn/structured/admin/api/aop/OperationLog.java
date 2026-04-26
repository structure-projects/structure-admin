package cn.structured.admin.api.aop;

/**
 *  操作日志注解 ，TODO 去实现这个注解的日志写入
 */
public @interface OperationLog {

    String value() default "";

    String module() default "";
}
