package cn.structured.admin.service.impl;

import cn.structured.admin.mapper.MenuMapper;
import cn.structured.admin.entity.Menu;
import cn.structured.admin.service.IMenuService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 菜单管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public boolean save(Menu entity) {
        setTreePath(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Menu entity) {
        setTreePath(entity);
        return super.updateById(entity);
    }

    @Override
    public void enable(Long menuId) {
        Menu menu = new Menu();
        menu.setId(menuId);
        menu.setEnabled(true);
        baseMapper.updateById(menu);
    }

    @Override
    public void disable(Long menuId) {
        Menu menu = new Menu();
        menu.setId(menuId);
        menu.setEnabled(false);
        baseMapper.updateById(menu);
    }

    private void setTreePath(Menu entity) {
        Menu parent = baseMapper.selectById(entity.getParentId());
        if (null != parent &&  null != parent.getTreePath()) {
            entity.setTreePath(parent.getTreePath() + "," + entity.getParentId());
        } else {
            entity.setTreePath(entity.getParentId().toString());
        }
    }
}
