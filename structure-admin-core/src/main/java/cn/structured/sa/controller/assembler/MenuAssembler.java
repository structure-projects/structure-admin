package cn.structured.sa.controller.assembler;

import cn.hutool.core.util.ObjectUtil;
import cn.structured.sa.client.dto.menu.CreateMenuDTO;
import cn.structured.sa.client.dto.menu.UpdateMenuDTO;
import cn.structured.sa.client.vo.MenuDetailsVO;
import cn.structured.sa.client.vo.MenuVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.client.vo.UserMenuVO;
import cn.structured.sa.entity.Menu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class MenuAssembler {

    private MenuAssembler() {
    }

    /**
     * 装配成菜单实体
     *
     * @param createMenu 创建菜单DTO
     * @return 菜单实体
     */
    public static Menu assembler(CreateMenuDTO createMenu) {
        Menu menu = new Menu();
        menu.setPid(createMenu.getPid());
        menu.setName(createMenu.getName());
        menu.setCode(createMenu.getCode());
        menu.setType(createMenu.getType());
        menu.setIcon(createMenu.getIcon());
        menu.setRouter(createMenu.getRouter());
        menu.setComponent(createMenu.getComponent());
        menu.setPermission(createMenu.getPermission());
        menu.setOpenType(createMenu.getOpenType());
        menu.setVisible(createMenu.getVisible());
        menu.setLink(createMenu.getLink());
        menu.setRedirect(createMenu.getRedirect());
        menu.setMenuType(createMenu.getMenuType());
        menu.setSequence(createMenu.getSequence());
        menu.setRemark(createMenu.getRemark());
        return menu;
    }

    /**
     * 装配成菜单实体
     *
     * @param updateMenu 修改菜单DTO
     * @return 菜单实体
     */
    public static Menu assembler(UpdateMenuDTO updateMenu) {
        Menu menu = new Menu();
        menu.setId(updateMenu.getId());
        menu.setPid(updateMenu.getPid());
        menu.setName(updateMenu.getName());
        menu.setCode(updateMenu.getCode());
        menu.setType(updateMenu.getType());
        menu.setIcon(updateMenu.getIcon());
        menu.setRouter(updateMenu.getRouter());
        menu.setComponent(updateMenu.getComponent());
        menu.setPermission(updateMenu.getPermission());
        menu.setOpenType(updateMenu.getOpenType());
        menu.setVisible(updateMenu.getVisible());
        menu.setLink(updateMenu.getLink());
        menu.setRedirect(updateMenu.getRedirect());
        menu.setMenuType(updateMenu.getMenuType());
        menu.setSequence(updateMenu.getSequence());
        menu.setRemark(updateMenu.getRemark());
        return menu;
    }

    /**
     * 装配成菜单VO
     *
     * @param menu 菜单实体
     * @return 菜单列表VO
     */
    public static MenuVO assembler(Menu menu) {
        MenuVO menuVO = new MenuVO();
        menuVO.setId(menu.getId());
        menuVO.setPid(menu.getPid());
        menuVO.setName(menu.getName());
        menuVO.setCode(menu.getCode());
        menuVO.setType(menu.getType());
        menuVO.setIcon(menu.getIcon());
        menuVO.setRouter(menu.getRouter());
        menuVO.setOpenType(menu.getOpenType());
        menuVO.setPermission(menu.getPermission());
        menuVO.setSequence(menu.getSequence());
        menuVO.setMenuType(menu.getMenuType());
        menuVO.setRemark(menu.getRemark());
        menuVO.setEnabled(menu.getEnabled());
        menuVO.setOperator(menu.getOperator());
        menuVO.setOperatorTime(menu.getUpdateTime());
        menuVO.setOrganizationId(menu.getOrganizationId());
        menuVO.setHasChildren(ObjectUtil.isNotNull(menu.getChildren()));
        return menuVO;
    }

    /**
     * 装配成菜单详情VO
     *
     * @param menu 菜单实体数据
     * @return 菜单详情
     */
    public static MenuDetailsVO assemblerDetails(Menu menu) {
        MenuDetailsVO menuDetailsVO = new MenuDetailsVO();
        menuDetailsVO.setId(menu.getId());
        menuDetailsVO.setPid(menu.getPid());
        menuDetailsVO.setName(menu.getName());
        menuDetailsVO.setCode(menu.getCode());
        menuDetailsVO.setType(menu.getType());
        menuDetailsVO.setIcon(menu.getIcon());
        menuDetailsVO.setRouter(menu.getRouter());
        menuDetailsVO.setComponent(menu.getComponent());
        menuDetailsVO.setPermission(menu.getPermission());
        menuDetailsVO.setOpenType(menu.getOpenType());
        menuDetailsVO.setVisible(menu.getVisible());
        menuDetailsVO.setLink(menu.getLink());
        menuDetailsVO.setRedirect(menu.getRedirect());
        menuDetailsVO.setMenuType(menu.getMenuType());
        menuDetailsVO.setSequence(menu.getSequence());
        menuDetailsVO.setRemark(menu.getRemark());
        menuDetailsVO.setEnabled(menu.getEnabled());
        menuDetailsVO.setOperator(menu.getOperator());
        menuDetailsVO.setOperatorTime(menu.getUpdateTime());
        menuDetailsVO.setOrganizationId(menu.getOrganizationId());
        return menuDetailsVO;
    }

    public static List<UserMenuVO> assemblerUserMenu(List<Menu> menuList) {
        Map<Long, UserMenuVO> optionMap = menuList.stream().collect(Collectors.toMap(Menu::getId, MenuAssembler::assemblerUserMenu));
        List<UserMenuVO> parentOptionList = new ArrayList<>();
        menuList.forEach(menu -> {
            Long pid = menu.getPid();
            UserMenuVO parentOption = optionMap.get(pid);
            UserMenuVO currentOption = optionMap.get(menu.getId());
            if (null == parentOption) {
                parentOptionList.add(currentOption);
            } else {
                List<UserMenuVO> children = parentOption.getChildren();
                if (null == children) {
                    children = new ArrayList<>();
                    parentOption.setChildren(children);
                }
                children.add(currentOption);
            }
        });
        return parentOptionList;
    }

    private static UserMenuVO assemblerUserMenu(Menu menu) {
        UserMenuVO userMenuVO = new UserMenuVO();
        userMenuVO.setId(menu.getId());
        userMenuVO.setName(menu.getName());
        userMenuVO.setCode(menu.getCode());
        userMenuVO.setType(menu.getType());
        userMenuVO.setIcon(menu.getIcon());
        userMenuVO.setRouter(menu.getRouter());
        userMenuVO.setComponent(menu.getComponent());
        userMenuVO.setPermission(menu.getPermission());
        userMenuVO.setOpenType(menu.getOpenType());
        userMenuVO.setVisible(menu.getVisible());
        userMenuVO.setLink(menu.getLink());
        userMenuVO.setRedirect(menu.getRedirect());
        return userMenuVO;
    }
}
