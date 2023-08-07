package cn.structured.sa.mapper;

import cn.structured.mybatis.plus.starter.base.IBaseMapper;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author chuck
 * @since 2023-07-06
 */
public interface RoleMapper extends IBaseMapper<Role> {

    /**
     * 通过据说ID查询角色下的所有权限
     *
     * @param roleIds 角色ID
     * @return 权限菜单
     */
    @Select("SELECT m.id, m.code, m.name\n" +
            "FROM menu m\n" +
            "         LEFT JOIN role_menu rm on m.id = rm.menu_id\n" +
            "WHERE m.is_deleted = 0 AND m.is_enabled = 1 AND  rm.role_id in (#{roleIds})")
    List<Menu> selectMenuByRole(@Param("roleIds") List<Long> roleIds);

    @Select("SELECT m.id \n" +
            "FROM menu m\n" +
            "         LEFT JOIN role_menu rm on m.id = rm.menu_id\n" +
            "WHERE rm.role_id = #{roleId} AND m.is_deleted = 0 AND m.is_enabled = 1")
    List<Long> selectAuthoritiesByRoleId(@Param("roleId") Long roleId);

}
