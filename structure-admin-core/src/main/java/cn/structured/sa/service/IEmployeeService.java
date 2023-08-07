package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.bo.SaveEmployeeBO;
import cn.structured.sa.bo.UpdateEmployeeBO;
import cn.structured.sa.client.vo.EmployeeDetailsVO;
import cn.structured.sa.entity.Employee;

/**
 * 职工Service
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IEmployeeService extends IBaseService<Employee> {

    /**
     * 获取职工详情
     *
     * @param empId 职工ID
     * @return 职工详情
     */
    EmployeeDetailsVO getEmployeeById(Long empId);

}
