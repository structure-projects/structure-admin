package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Dept;

/**
 * 部门Service
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IDeptService extends IBaseService<Dept> {


    /**
     * 启用
     *
     * @param deptId 部门ID
     */
    void enable(Long deptId);

    /**
     * 停用
     *
     * @param deptId 部门ID
     */
    void disable(Long deptId);

}
