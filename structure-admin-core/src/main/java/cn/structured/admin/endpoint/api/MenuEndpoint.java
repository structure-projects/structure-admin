package cn.structured.admin.endpoint.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.manager.IUserManager;
import cn.structured.admin.dto.MenuDTO;
import cn.structured.admin.vo.RouteVO;
import cn.structured.admin.endpoint.assembler.MenuAssembler;
import cn.structured.admin.endpoint.assembler.OptionAssembler;
import cn.structured.admin.entity.Menu;
import cn.structured.admin.service.IMenuService;
import cn.structured.admin.vo.MenuDetailsVO;
import cn.structured.admin.vo.MenuVO;
import cn.structured.admin.vo.OptionVO;
import cn.structured.security.util.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能管理
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@Api(tags = "功能管理")
@Slf4j
@RestController
@RequestMapping(value = "/api/menus")
public class MenuEndpoint {

    @Resource
    private IMenuService menuService;

    @Resource
    private IUserManager userManager;

    @ApiOperation(value = "新增功能")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated MenuDTO createMenu) {
        Menu menu = MenuAssembler.assembler(createMenu);
        menuService.save(menu);
        return ResultUtilSimpleImpl.success(menu.getId());
    }

    @ApiOperation(value = "修改功能")
    @PutMapping(value = "/{menuId}")
    public ResResultVO<Void> update(@ApiParam(value = "功能ID", example = "1645717015337684992")
                                    @PathVariable("menuId") Long menuId,
                                    @RequestBody @Validated MenuDTO updateMenu) {
        Menu assembler = MenuAssembler.assembler(updateMenu);
        assembler.setId(menuId);
        menuService.updateById(assembler);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "功能列表")
    @GetMapping(value = "/list")
    public ResResultVO<List<MenuVO>> list(@ApiParam(value = "关键字", example = "菜单名词") String keywords) {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery()
                .like(StrUtil.isNotBlank(keywords), Menu::getName, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .orderByAsc(Menu::getSort);
        List<Menu> menuList = menuService.list(queryWrapper);
        Map<Long, MenuVO> menuMap = menuList
                .stream()
                .collect(Collectors.toMap(Menu::getId, MenuAssembler::assembler));
        List<MenuVO> parentList = new ArrayList<>();
        menuList.forEach(item -> {
            Long pid = item.getParentId();
            Long id = item.getId();
            MenuVO parentOption = menuMap.get(pid);
            MenuVO currentOption = menuMap.get(id);
            if (null == parentOption) {
                parentList.add(currentOption);
            } else {
                List<MenuVO> children = parentOption.getChildren();
                if (null == children) {
                    children = new ArrayList<>();
                    parentOption.setChildren(children);
                }
                children.add(currentOption);
            }
        });
        return ResultUtilSimpleImpl.success(parentList);
    }


    @ApiOperation(value = "查看功能详情")
    @GetMapping(value = "/{menuId}")
    public ResResultVO<MenuDetailsVO> get(@ApiParam(value = "功能ID", example = "1645717015337684992")
                                          @PathVariable("menuId")
                                          Long menuId) {
        Menu menu = menuService.getById(menuId);
        return ResultUtilSimpleImpl.success(MenuAssembler.assemblerDetails(menu));
    }

    @ApiOperation(value = "删除功能")
    @DeleteMapping(value = "/{menuId}")
    public ResResultVO<Void> remove(@ApiParam(value = "功能ID", example = "1645717015337684992")
                                    @PathVariable("menuId") Long menuId) {
        menuService.removeById(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{menuId}")
    public ResResultVO<Void> enable(@ApiParam(value = "功能ID", example = "1645717015337684992")
                                    @PathVariable("menuId") Long menuId) {
        menuService.enable(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{menuId}")
    public ResResultVO<Void> disable(@ApiParam(value = "功能ID", example = "1645717015337684992")
                                     @PathVariable("menuId") Long menuId) {
        menuService.disable(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "功能菜单树", notes = "功能菜单树 TREE结构")
    @GetMapping(value = "/options")
    public ResResultVO<List<OptionVO>> option() {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getEnabled, Boolean.TRUE)
                .select(Menu::getId, Menu::getCode, Menu::getPath, Menu::getName, Menu::getParentId)
                .orderByAsc(Menu::getSort);
        List<Menu> menuList = menuService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(OptionAssembler.assemblerForMenu(menuList));
    }

    @ApiOperation(value = "获取当前用户的功能菜单", notes = "功能菜单树 TREE结构")
    @GetMapping(value = "/routes")
    public ResResultVO<List<RouteVO>> getMenuList() {
        Long userId = SecurityUtils.getUserId();
        List<String> userRole = userManager.getUserRole(userId);
        List<String> userAuthorities = userManager.getUserAuthorities(userId);
        if (CollUtil.isNotEmpty(userAuthorities)) {
            LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery()
                    .eq(Menu::getEnabled, Boolean.TRUE)
                    .ne(Menu::getType, 4)
                    .in(Menu::getCode, userAuthorities)
                    .orderByAsc(Menu::getSort);
            List<Menu> menuList = menuService.list(queryWrapper);
            Map<Long, RouteVO> groupMap = menuList
                    .stream()
                    .collect(Collectors.toMap(Menu::getId, e -> MenuAssembler.assemblerRote(e, userRole)));
            List<RouteVO> parenList = new ArrayList<>();
            menuList.forEach(menu -> {
                Long pid = menu.getParentId();
                Long id = menu.getId();
                RouteVO parent = groupMap.get(pid);
                RouteVO current = groupMap.get(id);
                if (null == parent) {
                    parenList.add(current);
                } else {
                    List<RouteVO> children = parent.getChildren();
                    if (null == children) {
                        children = new ArrayList<>();
                        parent.setChildren(children);
                    }
                    children.add(current);
                }
            });
            return ResultUtilSimpleImpl.success(parenList);
        } else {
            return ResultUtilSimpleImpl.success(new ArrayList<>());
        }

    }
}
