package cn.structured.sa.mapper;

import cn.structured.mybatis.plus.starter.base.IBaseMapper;
import cn.structured.sa.entity.Role;
import cn.structured.sa.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author chuck
 * @since 2023-07-06
 */
public interface UserRoleMapper extends IBaseMapper<UserRole> {


    @Select("SELECT r.id, r.name, r.code from role r\n" +
            "    LEFT JOIN user_role ur on r.id = ur.role_id AND r.is_deleted = 0 ANd r.is_enabled = 1\n" +
            "WHERE ur.user_id = #{userId} ")
    List<Role> selectRoleByUserId(@Param("userId") Long userId);

}
