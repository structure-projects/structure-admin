package cn.structured.admin.endpoint.api;


import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.dto.DeptDTO;
import cn.structured.admin.endpoint.assembler.DeptAssembler;
import cn.structured.admin.endpoint.assembler.OptionAssembler;
import cn.structured.admin.entity.Dept;
import cn.structured.admin.service.IDeptService;
import cn.structured.admin.vo.DeptVO;
import cn.structured.admin.vo.OptionVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
 * 部门管理
 * @author chuck
 * @version 2024/07/19 下午11:40
 * @since 1.8
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping(value = "/api/dept")
public class DeptEndpoint {

    @Resource
    private IDeptService service;

    @ApiOperation(value = "新增部门")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated DeptDTO create) {
        Dept dept = DeptAssembler.assembler(create);
        service.save(dept);
        return ResultUtilSimpleImpl.success(dept.getId());
    }

    @ApiOperation(value = "修改部门")
    @PutMapping(value = "/{id}")
    public ResResultVO<Void> update(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                    @PathVariable("id") Long id,
                                    @RequestBody @Validated DeptDTO update) {
        Dept dept = DeptAssembler.assembler(update);
        dept.setId(id);
        service.updateById(dept);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "部门列表")
    @GetMapping(value = "/list")
    public ResResultVO<List<DeptVO>> page(@ApiParam(value = "关键字", example = "部门key")
                                          @RequestParam(required = false)
                                          String keywords,
                                          @ApiParam(value = "是否启用", example = "TRUE")
                                          @RequestParam(required = false) Boolean enabled) {
        LambdaQueryWrapper<Dept> queryWrapper = Wrappers.<Dept>lambdaQuery()
                .eq(null != enabled, Dept::getEnabled, enabled)
                .like(StrUtil.isNotBlank(keywords), Dept::getName, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .orderByAsc(Dept::getSort);
        List<Dept> deptList = service.list(queryWrapper);
        Map<Long, DeptVO> optionMap = deptList
                .stream()
                .collect(Collectors.toMap(Dept::getId, DeptAssembler::assemblerDept));
        List<DeptVO> parentDeptList = new ArrayList<>();
        deptList.forEach(dept -> {
            Long pid = dept.getParentId();
            Long id = dept.getId();
            DeptVO parentOption = optionMap.get(pid);
            DeptVO currentOption = optionMap.get(id);
            if (null == parentOption) {
                parentDeptList.add(currentOption);
            } else {
                List<DeptVO> children = parentOption.getChildren();
                if (null == children) {
                    children = new ArrayList<>();
                    parentOption.setChildren(children);
                }
                children.add(currentOption);
            }
        });
        return ResultUtilSimpleImpl.success(parentDeptList);
    }


    @ApiOperation(value = "查看部门详情")
    @GetMapping(value = "/{id}")
    public ResResultVO<DeptVO> get(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                   @PathVariable("id")
                                   Long id) {
        Dept dept = service.getById(id);
        return ResultUtilSimpleImpl.success(DeptAssembler.assemblerDept(dept));
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping(value = "/{ids}")
    public ResResultVO<Void> remove(@ApiParam(value = "部门ID", example = "1645717015337684992")
                                    @PathVariable("ids") List<Long> ids) {
        service.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "功能菜单树", notes = "功能菜单树 TREE结构")
    @GetMapping(value = "/options")
    public ResResultVO<List<OptionVO>> option() {
        LambdaQueryWrapper<Dept> queryWrapper = Wrappers.<Dept>lambdaQuery()
                .eq(Dept::getEnabled, Boolean.TRUE)
                .select(Dept::getId, Dept::getName, Dept::getParentId)
                .orderByAsc(Dept::getSort);
        List<Dept> deptList = service.list(queryWrapper);
        return ResultUtilSimpleImpl.success(OptionAssembler.assemblerForDept(deptList));
    }

}
