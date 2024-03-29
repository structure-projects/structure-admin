package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Menu;

/**
 * 功能菜单Service
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IMenuService extends IBaseService<Menu> {


    /**
     * 启用
     *
     * @param menuId 菜单ID
     */
    void enable(Long menuId);

    /**
     * 停用
     *
     * @param menuId 菜单ID
     */
    void disable(Long menuId);

}
