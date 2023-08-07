package cn.structured.sa.controller.assembler;

import cn.hutool.core.util.StrUtil;
import cn.structured.sa.bo.SaveEmployeeBO;
import cn.structured.sa.bo.UpdateEmployeeBO;
import cn.structured.sa.client.dto.employee.CreateEmployeeDTO;
import cn.structured.sa.client.dto.employee.UpdateEmployeeDTO;
import cn.structured.sa.client.vo.EmployeeDetailsVO;
import cn.structured.sa.client.vo.EmployeeVO;
import cn.structured.sa.entity.Employee;
import cn.structured.sa.entity.User;

/**
 * 职工装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class EmployeeAssembler {

    private EmployeeAssembler() {
    }

    /**
     * 装配成职工
     *
     * @param updateEmployee 修改职工DTO
     * @return 职工
     */
    public static Employee assembler(UpdateEmployeeDTO updateEmployee) {
        Employee employee = new Employee();
        employee.setId(updateEmployee.getId());
        employee.setEmpNo(updateEmployee.getEmpNo());
        employee.setName(updateEmployee.getName());
        employee.setSex(updateEmployee.getSex());
        employee.setBirthDate(updateEmployee.getBirthDate());
        employee.setEducation(updateEmployee.getEducation());
        employee.setHealth(updateEmployee.getHealth());
        employee.setMarriage(updateEmployee.getMarriage());
        employee.setNation(updateEmployee.getNation());
        employee.setPolitics(updateEmployee.getPolitics());
        employee.setPhone(updateEmployee.getPhone());
        employee.setIdCard(updateEmployee.getIdCard());
        employee.setAccountAddress(updateEmployee.getAccountAddress());
        employee.setResidenceAddress(updateEmployee.getResidenceAddress());
        employee.setEmergencyContact(updateEmployee.getEmergencyContact());
        employee.setEmergencyContactPhone(updateEmployee.getEmergencyContactPhone());
        String dept = updateEmployee.getDept();
        employee.setDeptId(getDeptId(dept));
        employee.setDept(updateEmployee.getDept());
        employee.setPositionLevel(updateEmployee.getPositionLevel());
        employee.setPosition(updateEmployee.getPosition());
        employee.setState(updateEmployee.getState());
        employee.setDuty(updateEmployee.getDuty());
        return employee;
    }

    /**
     * 装配成职工
     *
     * @param createEmployee 创建职工DTO
     * @return 职工
     */
    public static Employee assembler(CreateEmployeeDTO createEmployee) {
        Employee employee = new Employee();
        employee.setName(createEmployee.getName());
        employee.setSex(createEmployee.getSex());
        employee.setBirthDate(createEmployee.getBirthDate());
        employee.setEducation(createEmployee.getEducation());
        employee.setHealth(createEmployee.getHealth());
        employee.setMarriage(createEmployee.getMarriage());
        employee.setNation(createEmployee.getNation());
        employee.setPolitics(createEmployee.getPolitics());
        employee.setPhone(createEmployee.getPhone());
        employee.setIdCard(createEmployee.getIdCard());
        employee.setAccountAddress(createEmployee.getAccountAddress());
        employee.setResidenceAddress(createEmployee.getResidenceAddress());
        employee.setEmergencyContact(createEmployee.getEmergencyContact());
        employee.setEmergencyContactPhone(createEmployee.getEmergencyContactPhone());
        String dept = createEmployee.getDept();
        employee.setDeptId(getDeptId(dept));
        employee.setDept(createEmployee.getDept());
        employee.setPositionLevel(createEmployee.getPositionLevel());
        employee.setPosition(createEmployee.getPosition());
        employee.setState(createEmployee.getState());
        employee.setDuty(createEmployee.getDuty());
        return employee;
    }


    /**
     * 转换为职工VO
     *
     * @param employee 职工业务对象
     * @return 职工VO对象
     */
    public static EmployeeVO assembler(Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(employee.getId());
        employeeVO.setEmpNo(employee.getEmpNo());
        employeeVO.setName(employee.getName());
        employeeVO.setSex(employee.getSex());
        employeeVO.setPhone(employee.getPhone());
        employeeVO.setDept(employee.getDept());
        employeeVO.setState(employee.getState());
        employeeVO.setOrganizationId(employee.getOrganizationId());
        employeeVO.setUserId(employee.getUserId());
        employeeVO.setOperator(employee.getOperator());
        employeeVO.setOperatorTime(employee.getUpdateTime());
        return employeeVO;
    }


    /**
     * 装配成职工详情
     *
     * @param employee 职工实体对象
     * @return 职工详情VO
     */
    public static EmployeeDetailsVO assemblerDetails(Employee employee) {
        EmployeeDetailsVO employeeDetailsVO = new EmployeeDetailsVO();
        employeeDetailsVO.setId(employee.getId());
        employeeDetailsVO.setEmpNo(employee.getEmpNo());
        employeeDetailsVO.setName(employee.getName());
        employeeDetailsVO.setSex(employee.getSex());
        employeeDetailsVO.setBirthDate(employee.getBirthDate());
        employeeDetailsVO.setEducation(employee.getEducation());
        employeeDetailsVO.setHealth(employee.getHealth());
        employeeDetailsVO.setMarriage(employee.getMarriage());
        employeeDetailsVO.setNation(employee.getNation());
        employeeDetailsVO.setPolitics(employee.getPolitics());
        employeeDetailsVO.setPhone(employee.getPhone());
        employeeDetailsVO.setIdCard(employee.getIdCard());
        employeeDetailsVO.setAccountAddress(employee.getAccountAddress());
        employeeDetailsVO.setResidenceAddress(employee.getResidenceAddress());
        employeeDetailsVO.setEmergencyContact(employee.getEmergencyContact());
        employeeDetailsVO.setEmergencyContactPhone(employee.getEmergencyContactPhone());
        employeeDetailsVO.setDept(employee.getDept());
        employeeDetailsVO.setDuty(employee.getDuty());
        employeeDetailsVO.setPositionLevel(employee.getPositionLevel());
        employeeDetailsVO.setPosition(employee.getPosition());
        employeeDetailsVO.setState(employee.getState());
        employeeDetailsVO.setOrganizationId(employee.getOrganizationId());
        employeeDetailsVO.setOperator(employee.getOperator());
        employeeDetailsVO.setOperatorTime(employee.getUpdateTime());
        return employeeDetailsVO;
    }

    private static Long getDeptId(String dept) {
        return Long.parseLong(dept.substring(dept.lastIndexOf(StrUtil.COMMA) + 1));
    }

}
