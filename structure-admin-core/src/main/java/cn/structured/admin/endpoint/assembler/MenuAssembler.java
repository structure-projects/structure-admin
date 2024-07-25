package cn.structured.admin.endpoint.assembler;

import cn.hutool.core.util.StrUtil;
import cn.structured.admin.dto.MenuDto;
import cn.structured.admin.dto.RouteVo;
import cn.structured.admin.entity.Menu;
import cn.structured.admin.enums.MenuTypeEnum;
import cn.structured.admin.vo.MenuDetailsVo;
import cn.structured.admin.vo.MenuVo;
import cn.structured.basic.core.utils.SystemUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;

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
     * @param menuDto 创建菜单DTO
     * @return 菜单实体
     */
    public static Menu assembler(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setCode(menuDto.getCode());
        menu.setParentId(menuDto.getParentId());
        menu.setType(menuDto.getType().getValue());
        menu.setName(menuDto.getName());
        menu.setPath(menuDto.getPath());
        menu.setComponent(menuDto.getComponent());
        menu.setPerm(menuDto.getPerm());
        menu.setIcon(menuDto.getIcon());
        menu.setSort(menuDto.getSort());
        menu.setVisible(menuDto.getVisible());
        menu.setRedirect(menuDto.getRedirect());
        // todo menu.setTreePath();
        menu.setAlwaysShow(menuDto.getAlwaysShow());
        menu.setKeepAlive(menuDto.getKeepAlive());
        menu.setOrganizationId(SystemUtil.getOrganizationId());
        return menu;
    }

    public static RouteVo assemblerRote(Menu menu, List<String> userRole) {
        String routeName = StringUtils.capitalize(StrUtil.toCamelCase(menu.getPath()));
        RouteVo routeVo = new RouteVo();
        routeVo.setId(menu.getId());
        routeVo.setParentId(menu.getParentId());
        routeVo.setPath(menu.getPath());
        routeVo.setComponent(menu.getComponent());
        routeVo.setRedirect(menu.getRedirect());
        routeVo.setName(routeName);
        routeVo.setChildren(Lists.newArrayList());
        RouteVo.Meta meta = new RouteVo.Meta();
        meta.setTitle(menu.getName());
        meta.setIcon(menu.getIcon());
        meta.setHidden(!menu.getVisible());
        meta.setRoles(userRole);
        meta.setKeepAlive(menu.getKeepAlive());
        meta.setAlwaysShow(menu.getAlwaysShow());
        routeVo.setMeta(meta);
        return routeVo;
    }

    /**
     * 装配成菜单VO
     *
     * @param menu 菜单实体
     * @return 菜单列表VO
     */
    public static MenuVo assembler(Menu menu) {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(menu.getId());
        menuVo.setCode(menu.getCode());
        menuVo.setParentId(menu.getParentId());
        menuVo.setName(menu.getName());
        menuVo.setType(MenuTypeEnum.getMenuTypeEnum(menu.getType()));
        menuVo.setPath(menu.getPath());
        menuVo.setComponent(menu.getComponent());
        menuVo.setSort(menu.getSort());
        menuVo.setVisible(menu.getVisible());
        menuVo.setIcon(menu.getIcon());
        menuVo.setRedirect(menu.getRedirect());
        menuVo.setPerm(menu.getPerm());
        return menuVo;
    }

    /**
     * 装配成菜单详情VO
     *
     * @param menu 菜单实体数据
     * @return 菜单详情
     */
    public static MenuDetailsVo assemblerDetails(Menu menu) {
        MenuDetailsVo menuDetailsVo = new MenuDetailsVo();
        menuDetailsVo.setId(menu.getId());
        menuDetailsVo.setCode(menu.getCode());
        menuDetailsVo.setParentId(menu.getParentId());
        menuDetailsVo.setName(menu.getName());
        menuDetailsVo.setType(MenuTypeEnum.getMenuTypeEnum(menu.getType()));
        menuDetailsVo.setPath(menu.getPath());
        menuDetailsVo.setComponent(menu.getComponent());
        menuDetailsVo.setPerm(menu.getPerm());
        menuDetailsVo.setVisible(menu.getVisible());
        menuDetailsVo.setSort(menu.getSort());
        menuDetailsVo.setIcon(menu.getIcon());
        menuDetailsVo.setRedirect(menu.getRedirect());
        menuDetailsVo.setKeepAlive(menu.getKeepAlive());
        menuDetailsVo.setAlwaysShow(menu.getAlwaysShow());
        return menuDetailsVo;
    }
}
