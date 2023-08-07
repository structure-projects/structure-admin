package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.menu.CreateMenuDTO;
import cn.structured.sa.client.dto.menu.SearchMenuDTO;
import cn.structured.sa.client.dto.menu.UpdateMenuDTO;
import cn.structured.sa.client.vo.DeptVO;
import cn.structured.sa.client.vo.MenuDetailsVO;
import cn.structured.sa.client.vo.MenuVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.controller.assembler.DeptAssembler;
import cn.structured.sa.controller.assembler.MenuAssembler;
import cn.structured.sa.controller.assembler.OptionAssembler;
import cn.structured.sa.entity.Dept;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.group.ListGroup;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单管理
 * todo 初始数据的定义 测试用户加载动态路由 并添加用户功能按钮的权限，支持微前端路由，业务底座加载路由插件
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "菜单管理")
@RequestMapping(value = "/menu")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService menuService;

    @ApiOperation(value = "新增菜单")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateMenuDTO createMenu) {
        Menu menu = MenuAssembler.assembler(createMenu);
        menuService.save(menu);
        return ResultUtilSimpleImpl.success(menu.getId());
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateMenuDTO updateMenu) {
        menuService.updateById(MenuAssembler.assembler(updateMenu));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "菜单分页列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<MenuVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                             @PathVariable("page") Long page,
                                             @ApiParam(value = "页大小", required = true, example = "20")
                                             @PathVariable("pageSize") Long pageSize,
                                             SearchMenuDTO searchMenu) {
        Menu menu = new Menu();
        menu.setName(searchMenu.getName());
        menu.setCode(searchMenu.getCode());
        menu.setOrganizationId(UserUtil.getOrganizationId());
        menu.setEnabled(searchMenu.getEnabled());
        QueryJoinPageListWrapper<Menu> queryWrapper = new QueryJoinPageListWrapper<>(menu);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Menu> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Menu> pageResult = menuService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, MenuAssembler::assembler));
    }

    @ApiOperation(value = "部门子列表")
    @GetMapping(value = "/list/children")
    public ResResultVO<List<MenuVO>> children(@ApiParam(value = "菜单父ID", example = "1645717015337684992") @RequestParam(value = "parentId") Long parentId) {
        Menu menu = new Menu();
        menu.setPid(parentId);
        menu.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Menu> queryWrapper = new QueryJoinPageListWrapper<>(menu);
        queryWrapper.setIsJoin(true);
        queryWrapper.setJoinGroup(ListGroup.class);
        List<Menu> result = menuService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(result.stream()
                .map(MenuAssembler::assembler)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "查看菜单详情")
    @GetMapping(value = "/")
    public ResResultVO<MenuDetailsVO> get(@ApiParam(value = "菜单ID", example = "1645717015337684992")
                                                  Long menuId) {
        Menu menu = menuService.getById(menuId);
        return ResultUtilSimpleImpl.success(MenuAssembler.assemblerDetails(menu));
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping(value = "/{menuId}")
    public ResResultVO<Void> remove(@ApiParam(value = "菜单ID", example = "1645717015337684992")
                                    @PathVariable("menuId") Long menuId) {
        menuService.removeById(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{menuId}")
    public ResResultVO<Void> enable(@ApiParam(value = "菜单ID", example = "1645717015337684992")
                                    @PathVariable("menuId") Long menuId) {
        menuService.enable(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{menuId}")
    public ResResultVO<Void> disable(@ApiParam(value = "菜单ID", example = "1645717015337684992")
                                     @PathVariable("menuId") Long menuId) {
        menuService.disable(menuId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "菜单树", notes = "菜单权限树 TREE结构")
    @GetMapping(value = "/option")
    public ResResultVO<List<OptionVO>> option() {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getEnabled, Boolean.TRUE)
                .eq(Menu::getOrganizationId, UserUtil.getOrganizationId())
                .select(Menu::getId, Menu::getCode, Menu::getName, Menu::getPid);
        List<Menu> menuList = menuService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(OptionAssembler.assemblerForMenu(menuList));
    }
}
