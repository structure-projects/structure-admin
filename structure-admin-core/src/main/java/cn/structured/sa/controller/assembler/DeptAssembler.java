package cn.structured.sa.controller.assembler;

import cn.hutool.core.util.ObjectUtil;
import cn.structured.sa.client.dto.dept.CreateDeptDTO;
import cn.structured.sa.client.dto.dept.UpdateDeptDTO;
import cn.structured.sa.client.vo.DeptVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.entity.Dept;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class DeptAssembler {

    private DeptAssembler() {
    }

    public static DeptVO assembler(Dept dept) {
        DeptVO deptVO = new DeptVO();
        deptVO.setId(dept.getId());
        deptVO.setPid(dept.getPid());
        deptVO.setPids(dept.getPids());
        deptVO.setSequence(dept.getSequence());
        deptVO.setName(dept.getName());
        deptVO.setCode(dept.getCode());
        deptVO.setRemark(dept.getRemark());
        deptVO.setEnabled(dept.getEnabled());
        deptVO.setOperator(dept.getOperator());
        deptVO.setHasChildren(ObjectUtil.isNotNull(dept.getChildren()));
        deptVO.setOperatorTime(dept.getUpdateTime());
        deptVO.setOrganizationId(dept.getOrganizationId());
        return deptVO;
    }

    public static Dept assembler(CreateDeptDTO createDept) {
        Dept dept = new Dept();
        dept.setPid(createDept.getPid());
        dept.setName(createDept.getName());
        dept.setCode(createDept.getCode());
        dept.setSequence(createDept.getSequence());
        dept.setRemark(createDept.getRemark());
        return dept;
    }

    public static Dept assembler(UpdateDeptDTO updateDept) {
        Dept dept = new Dept();
        dept.setId(updateDept.getId());
        dept.setPid(updateDept.getPid());
        dept.setName(updateDept.getName());
        dept.setCode(updateDept.getCode());
        dept.setSequence(updateDept.getSequence());
        dept.setRemark(updateDept.getRemark());
        return dept;
    }
}
