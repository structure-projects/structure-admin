package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.mapper.MenuMapper;
import cn.structured.sa.service.IMenuService;
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
}
