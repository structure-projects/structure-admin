package cn.structured.admin.api.utils;

import cn.structure.common.constant.AuthConstant;
import cn.structure.common.exception.CommonException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统工具类
 *
 * @author chuck
 */
public class SystemUtil {

    /**
     * 获取
     *
     * @return Long
     */
    public static Long getOrganizationId() {
        //从请求头中获取机构信息，如果获取不到则默认为0L
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //request
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String headerOrganizationId = request.getHeader(AuthConstant.HEADER_ORGANIZATION_ID);
        try {
            return Long.parseLong(headerOrganizationId);
        } catch (Exception e) {
            throw new CommonException("", "租户不存在！");
        }
    }

    public static Long getDeptId() {
        //从请求头中获取机构信息，如果获取不到则默认为0L
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //request
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String deptId = request.getHeader("X-DEPT_ID");
        try {
            return Long.parseLong(deptId);
        } catch (Exception e) {
            return null;
        }
    }
}
