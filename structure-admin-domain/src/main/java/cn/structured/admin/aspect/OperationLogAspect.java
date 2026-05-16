package cn.structured.admin.aspect;

import cn.structure.common.constant.AuthConstant;
import cn.structure.starter.tenant.TenantContextHolder;
import cn.structured.admin.api.aop.OperationLog;
import cn.structured.admin.entity.OperationRecord;
import cn.structured.admin.service.IOperationRecordService;
import cn.structured.security.util.SecurityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class OperationLogAspect {

    private final IOperationRecordService operationRecordService;
    private final ObjectMapper objectMapper;

    @Pointcut("@annotation(cn.structured.admin.api.aop.OperationLog)")
    public void operationLogPointcut() {
    }

    @Around("operationLogPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        OperationRecord operationRecord = new OperationRecord();
        try {
            // 获取请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                operationRecord.setIpAddress(request.getRemoteAddr());
                operationRecord.setUserAgent(request.getHeader("User-Agent"));
            }

            // 获取注解信息
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog operationLog = method.getAnnotation(OperationLog.class);
            if (operationLog != null) {
                operationRecord.setAction(operationLog.value());
                // 这里可以根据需要将模块字符串转换为数字

                // 从配置中获取模块ID
                // operationRecord.setModule(Integer.parseInt(operationLog.module()));
            }

            // 获取方法参数
            Object[] args = joinPoint.getArgs();
            try {
                operationRecord.setOperationParams(JSON.toJSONString(args));
            } catch (Exception e) {
                log.error("序列化参数失败", e);
                operationRecord.setOperationParams("参数序列化失败");
            }

            // 执行方法
            Object result = joinPoint.proceed();

            // 记录成功信息
            operationRecord.setStatus(1);
            try {
                operationRecord.setOperationResult(JSON.toJSONString(result));
            } catch (Exception e) {
                log.error("序列化结果失败", e);
                operationRecord.setOperationResult("结果序列化失败");
            }

            return result;
        } catch (Exception e) {
            // 记录失败信息
            operationRecord.setStatus(0);
            operationRecord.setErrorMsg(e.getMessage());
            throw e;
        } finally {
            // 计算耗时
            long endTime = System.currentTimeMillis();
            operationRecord.setCostTime(endTime - startTime);
            // 这里可以设置操作用户ID，需要从上下文中获取
             operationRecord.setMid(getCurrentUserId());
            // 保存操作日志
            try {
                operationRecordService.save(operationRecord);
            } catch (Exception e) {
                log.error("保存操作日志失败", e);
            }
        }
    }

    // 这里需要实现获取当前用户ID的方法
    private Long getCurrentUserId() {
        // 从上下文或会话中获取当前用户ID
        try {
            SecurityUtils.getUser();
            JSONObject user = JSON.parseObject(JSON.toJSONString(SecurityUtils.getUser()));
            return null == user.getLong(AuthConstant.USER_ID) ? user.getLong("id") : user.getLong(AuthConstant.USER_ID);
        }catch (Exception  e) {
            log.warn("获取当前用户ID失败", e);
        }
        return null;
    }
}
