package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.sa.bo.SaveEmployeeBO;
import cn.structured.sa.bo.UpdateEmployeeBO;
import cn.structured.sa.client.vo.EmployeeDetailsVO;
import cn.structured.sa.controller.assembler.EmployeeAssembler;
import cn.structured.sa.entity.Employee;
import cn.structured.sa.entity.User;
import cn.structured.sa.mapper.EmployeeMapper;
import cn.structured.sa.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 职工管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public EmployeeDetailsVO getEmployeeById(Long empId) {
        Employee employee = new Employee();
        employee.setId(empId);
        QueryJoinPageListWrapper<Employee> wrapper = new QueryJoinPageListWrapper<>(employee);
        List<Employee> employees = baseMapper.selectJoin(wrapper);
        return employees.stream()
                .map(EmployeeAssembler::assemblerDetails)
                .findFirst().orElse(new EmployeeDetailsVO());
    }

    @Override
    public boolean save(Employee entity) {
        //职工登记
        //todo 生成职工编号
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        //todo 校验是否和用户绑定 如果绑定用户则不能删除用户
        return super.removeById(id);
    }

    //todo 激活用户 流程职工登记后需要要求用户加入组织，激活则是用户确认加入组织 ,在邀请用户是时可以选择是否直接激活，如果用户没有注册则需要用户进行注册邀请参数为用户手机号系统可以根据用户是否注册过账户来进行邀请或注册邀请

    //邀请用户

    //用户激活

    //离职

    //待岗
}


