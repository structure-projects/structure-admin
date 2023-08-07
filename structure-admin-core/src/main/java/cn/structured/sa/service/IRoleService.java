package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Role;

import java.util.List;

/**
 * 角色管理service
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 启用
     *
     * @param roleId 角色ID
     */
    void enable(Long roleId);

    /**
     * 停用
     *
     * @param roleId 角色ID
     */
    void disable(Long roleId);

    /**
     * 获取角色的权限
     *
     * @param roleId 角色ID
     * @return
     */
    List<Long> getAuthorities(Long roleId);

}
