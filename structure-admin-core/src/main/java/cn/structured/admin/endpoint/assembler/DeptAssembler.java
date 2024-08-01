package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.dto.DeptDTO;
import cn.structured.admin.entity.Dept;
import cn.structured.admin.vo.DeptVO;
import cn.structured.basic.core.utils.SystemUtil;

public class DeptAssembler {


    public static DeptVO assemblerDept(Dept dept) {
        DeptVO deptVo = new DeptVO();
        deptVo.setId(dept.getId());
        deptVo.setName(dept.getName());
        deptVo.setParentId(dept.getParentId());
        deptVo.setTreePath(dept.getTreePath());
        deptVo.setSort(dept.getSort());
        deptVo.setEnabled(dept.getEnabled());
        deptVo.setCreateTime(dept.getCreateTime());
        deptVo.setUpdateTime(dept.getUpdateTime());
        return deptVo;
    }

    public static Dept assembler(DeptDTO deptDto) {
        Dept dept = new Dept();
        dept.setName(deptDto.getName());
        dept.setParentId(deptDto.getParentId());
        dept.setTreePath(deptDto.getTreePath());
        dept.setSort(deptDto.getSort());
        dept.setEnabled(deptDto.getEnabled());
        dept.setOrganizationId(SystemUtil.getOrganizationId());
        return dept;
    }


}
