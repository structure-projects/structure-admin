package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.employee.CreateEmployeeDTO;
import cn.structured.sa.client.dto.employee.SearchEmployeeDTO;
import cn.structured.sa.client.dto.employee.UpdateEmployeeDTO;
import cn.structured.sa.client.vo.EmployeeDetailsVO;
import cn.structured.sa.client.vo.EmployeeVO;
import cn.structured.sa.controller.assembler.EmployeeAssembler;
import cn.structured.sa.entity.Employee;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.IEmployeeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 职工管理
 * todo 邀请用户和用户激活离职和待岗状态变更,优化页面需要的枚举
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "职工管理")
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @ApiOperation(value = "职工登记")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateEmployeeDTO createEmployee) {
        Employee employee = EmployeeAssembler.assembler(createEmployee);
        employeeService.save(employee);
        return ResultUtilSimpleImpl.success(employee.getId());
    }

    @ApiOperation(value = "职工信息变更")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateEmployeeDTO updateEmployee) {
        employeeService.updateById(EmployeeAssembler.assembler(updateEmployee));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "职工分页列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<EmployeeVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                                 @PathVariable("page") Long page,
                                                 @ApiParam(value = "页大小", required = true, example = "20")
                                                 @PathVariable("pageSize") Long pageSize,
                                                 SearchEmployeeDTO searchEmployeeQuery) {
        Employee employee = new Employee();
        employee.setName(searchEmployeeQuery.getName());
        employee.setEmpNo(searchEmployeeQuery.getEmpNo());
        employee.setState(searchEmployeeQuery.getState());
        employee.setDeptId(searchEmployeeQuery.getDeptId());
        employee.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<Employee> queryWrapper = new QueryJoinPageListWrapper<>(employee);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Employee> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Employee> pageResult = employeeService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, EmployeeAssembler::assembler));
    }

    @ApiOperation(value = "职工详情")
    @GetMapping(value = "/")
    public ResResultVO<EmployeeDetailsVO> get(@ApiParam(value = "职工ID", required = true, example = "1645717015337684992")
                                              @RequestParam(value = "empId") Long empId) {
        return ResultUtilSimpleImpl.success(employeeService.getEmployeeById(empId));
    }


    @ApiOperation(value = "删除职工")
    @DeleteMapping(value = "/{empId}")
    public ResResultVO<Void> remove(@ApiParam(value = "职工ID", example = "1645717015337684992")
                                    @PathVariable("empId") Long empId) {
        employeeService.removeById(empId);
        return ResultUtilSimpleImpl.success(null);
    }
}
