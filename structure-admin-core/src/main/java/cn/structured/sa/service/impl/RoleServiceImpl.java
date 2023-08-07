package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Role;
import cn.structured.sa.entity.RoleMenu;
import cn.structured.sa.mapper.RoleMapper;
import cn.structured.sa.mapper.RoleMenuMapper;
import cn.structured.sa.service.IRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public void enable(Long roleId) {
        Role role = new Role();
        role.setId(roleId);
        role.setEnabled(true);
        baseMapper.updateById(role);
    }

    @Override
    public void disable(Long roleId) {
        Role role = new Role();
        role.setId(roleId);
        role.setEnabled(false);
        baseMapper.updateById(role);
    }

    @Override
    public List<Long> getAuthorities(Long roleId) {
        return baseMapper.selectAuthoritiesByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Role entity) {
        saveRoleMenu(entity);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Role entity) {
        roleMenuMapper.delete(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, entity.getId()));
        saveRoleMenu(entity);
        return super.updateById(entity);
    }

    private void saveRoleMenu(Role role) {
        //构建角色权限
        List<RoleMenu> roleMenus = role.getAuthorities().stream()
                .map(authorityId -> {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId(role.getId());
                    roleMenu.setMenuId(authorityId);
                    return roleMenu;
                }).collect(Collectors.toList());
        //判断权限不为空
        if (!roleMenus.isEmpty()){
            //插入权限
            roleMenuMapper.insertList(roleMenus);
        }
    }
}
