package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.role.CreateRoleDTO;
import cn.structured.sa.client.dto.role.SearchRoleDTO;
import cn.structured.sa.client.dto.role.UpdateRoleDTO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.client.vo.RoleVO;
import cn.structured.sa.controller.assembler.RoleAssembler;
import cn.structured.sa.entity.Role;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.IRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 角色管理
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "角色管理")
@RequestMapping(value = "/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateRoleDTO createRole) {
        Role role = RoleAssembler.assembler(createRole);
        roleService.save(role);
        return ResultUtilSimpleImpl.success(role.getId());
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateRoleDTO updateRole) {
        roleService.updateById(RoleAssembler.assembler(updateRole));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "角色列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<RoleVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                             @PathVariable("page") Long page,
                                             @ApiParam(value = "页大小", required = true, example = "20")
                                             @PathVariable("pageSize") Long pageSize,
                                             SearchRoleDTO searchRoleQuery) {
        Role role = new Role();
        role.setName(searchRoleQuery.getName());
        role.setCode(searchRoleQuery.getCode());
        role.setEnabled(searchRoleQuery.getEnabled());
        role.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Role> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Role> pageResult = roleService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, RoleAssembler::assembler));
    }

    @ApiOperation(value = "角色详情")
    @GetMapping(value = "/get")
    public ResResultVO<RoleVO> get(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                   @RequestParam("roleId") Long roleId) {
        Role role = new Role();
        role.setId(roleId);
        role.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);
        role = roleService.list(queryWrapper)
                .stream()
                .findFirst()
                .orElse(role);
        RoleVO resultRole = RoleAssembler.assembler(role);
        List<Long> authorities = roleService.getAuthorities(roleId);
        resultRole.setAuthorities(authorities);
        return ResultUtilSimpleImpl.success(resultRole);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/{roleId}")
    public ResResultVO<Void> remove(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                    @PathVariable("roleId") Long roleId) {
        roleService.removeById(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{roleId}")
    public ResResultVO<Void> enable(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                    @PathVariable("roleId") Long roleId) {
        roleService.enable(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{roleId}")
    public ResResultVO<Void> disable(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                     @PathVariable("roleId") Long roleId) {
        roleService.disable(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "角色列表")
    @GetMapping(value = "/list")
    public ResResultVO<List<OptionVO>> option(@ApiParam(value = "关键字", example = "name")
                                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        Role role = new Role();
        role.setEnabled(Boolean.TRUE);
        role.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setIsJoin(false);
        queryWrapper.addColumn("id","name", "code");
        queryWrapper.addSearch("name", "code");
        queryWrapper.setSearch(keyword);
        List<Role> pageResult = roleService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(pageResult
                .stream()
                .map(RoleAssembler::assemblerOption)
                .collect(Collectors.toList()));
    }

}
