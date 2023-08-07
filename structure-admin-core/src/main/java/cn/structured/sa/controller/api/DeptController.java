package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.dept.CreateDeptDTO;
import cn.structured.sa.client.dto.dept.SearchDeptDTO;
import cn.structured.sa.client.dto.dept.UpdateDeptDTO;
import cn.structured.sa.client.vo.DeptVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.controller.assembler.DeptAssembler;
import cn.structured.sa.controller.assembler.OptionAssembler;
import cn.structured.sa.entity.Dept;
import cn.structured.sa.group.ListGroup;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.IDeptService;
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
 * 部门管理
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "部门管理")
@RequestMapping(value = "/dept")
@RequiredArgsConstructor
public class DeptController {

    private final IDeptService deptService;

    @ApiOperation(value = "新增部门")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateDeptDTO createDept) {
        Dept dept = DeptAssembler.assembler(createDept);
        deptService.save(dept);
        return ResultUtilSimpleImpl.success(dept.getId());
    }

    @ApiOperation(value = "修改部门")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateDeptDTO updateDept) {
        deptService.updateById(DeptAssembler.assembler(updateDept));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "部门分页列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<DeptVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                             @PathVariable("page") Long page,
                                             @ApiParam(value = "页大小", required = true, example = "20")
                                             @PathVariable("pageSize") Long pageSize,
                                             SearchDeptDTO searchDept) {
        Dept dept = new Dept();
        dept.setName(searchDept.getName());
        dept.setCode(searchDept.getCode());
        dept.setEnabled(searchDept.getEnabled());
        dept.setPid(0L);
        dept.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Dept> queryWrapper = new QueryJoinPageListWrapper<>(dept);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Dept> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Dept> pageResult = deptService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, DeptAssembler::assembler));
    }

    @ApiOperation(value = "部门子列表")
    @GetMapping(value = "/list/children")
    public ResResultVO<List<DeptVO>> list(@ApiParam(value = "部门父ID", example = "1645717015337684992") @RequestParam(value = "parentId") Long parentId) {
        Dept dept = new Dept();
        dept.setPid(parentId);
        dept.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Dept> queryWrapper = new QueryJoinPageListWrapper<>(dept);
        queryWrapper.setIsJoin(true);
        queryWrapper.setJoinGroup(ListGroup.class);
        List<Dept> result = deptService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(result.stream()
                .map(DeptAssembler::assembler)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping(value = "/{deptId}")
    public ResResultVO<Void> remove(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                    @PathVariable("deptId") Long deptId) {
        deptService.removeById(deptId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{deptId}")
    public ResResultVO<Void> enable(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                    @PathVariable("deptId") Long deptId) {
        deptService.enable(deptId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{deptId}")
    public ResResultVO<Void> disable(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                     @PathVariable("deptId") Long deptId) {
        deptService.disable(deptId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "下拉选", notes = "当前组织下的所有部门- TREE结构")
    @GetMapping(value = "/option")
    public ResResultVO<List<OptionVO>> option() {
        LambdaQueryWrapper<Dept> queryWrapper = Wrappers.<Dept>lambdaQuery()
                .eq(Dept::getEnabled, Boolean.TRUE)
                .eq(Dept::getOrganizationId, UserUtil.getOrganizationId())
                .select(Dept::getId, Dept::getCode, Dept::getName, Dept::getPid);
        List<Dept> deptList = deptService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(OptionAssembler.assemblerForDept(deptList));
    }
}
