package cn.structured.sa.controller.assembler;

import cn.structured.sa.client.dto.role.CreateRoleDTO;
import cn.structured.sa.client.dto.role.UpdateRoleDTO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.client.vo.RoleVO;
import cn.structured.sa.entity.Role;
import cn.structured.sa.enums.RoleType;

/**
 * 角色装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class RoleAssembler {

    private RoleAssembler() {
    }

    /**
     * 创建角色DTO装配成角色
     *
     * @param createRole 创建角色
     * @return 角色实体对象
     */
    public static Role assembler(CreateRoleDTO createRole) {
        Role role = new Role();
        role.setName(createRole.getName());
        role.setCode(createRole.getCode());
        role.setRemark(createRole.getRemark());
        role.setType(RoleType.USER.getValue());
        role.setAuthorities(createRole.getAuthorities());
        return role;
    }

    /**
     * 修改角色DTO装配成角色
     *
     * @param updateRole 修改角色
     * @return 角色实体对象
     */
    public static Role assembler(UpdateRoleDTO updateRole) {
        Role role = new Role();
        role.setId(updateRole.getId());
        role.setName(updateRole.getName());
        role.setCode(updateRole.getCode());
        role.setRemark(updateRole.getRemark());
        role.setAuthorities(updateRole.getAuthorities());
        return role;
    }

    /**
     * 角色实体装配为角色VO
     *
     * @param role 角色实体
     * @return 返回角色VO对象
     */
    public static RoleVO assembler(Role role) {
        RoleVO roleVO = new RoleVO();
        roleVO.setId(role.getId());
        roleVO.setName(role.getName());
        roleVO.setCode(role.getCode());
        roleVO.setType(role.getType());
        roleVO.setRemark(role.getRemark());
        roleVO.setEnabled(role.getEnabled());
        roleVO.setOperator(role.getOperator());
        roleVO.setOperatorTime(role.getUpdateTime());
        roleVO.setOrganizationId(role.getOrganizationId());
        return roleVO;
    }

    /**
     * 角色装配成下拉选择VO对象
     *
     * @param role 角色
     * @return
     */
    public static OptionVO assemblerOption(Role role) {
        OptionVO option = new OptionVO();
        option.setId(role.getId());
        option.setName(role.getName());
        option.setCode(role.getCode());
        return option;
    }

}
