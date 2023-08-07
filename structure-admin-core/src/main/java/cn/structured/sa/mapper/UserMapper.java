package cn.structured.sa.mapper;

import cn.structured.mybatis.plus.starter.base.IBaseMapper;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.entity.Role;
import cn.structured.sa.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chuck
 * @since 2023-07-06
 */
public interface UserMapper extends IBaseMapper<User> {

    @Results(id = "menuResult",value = {
            @Result(column = "open_type",property = "openType",jdbcType = JdbcType.INTEGER),
            @Result(column = "is_visible",property = "visible",jdbcType = JdbcType.INTEGER),
            @Result(column = "menu_type",property = "menuType",jdbcType = JdbcType.INTEGER),
    })
    @Select("SELECT m.id,m.pid,m.name,m.code,m.type,m.icon,m.router,m.component,m.permission,m.open_type,m.is_visible,m.link,m.redirect from menu m " +
            "LEFT JOIN role_menu rm ON rm.menu_id = m.id AND m.is_deleted = 0 ANd m.is_enabled = 1 " +
            "LEFT JOIN role r ON r.id = rm.role_id  AND r.is_deleted = 0 ANd r.is_enabled = 1 " +
            "LEFT JOIN user_role ur on r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId} ORDER BY m.sequence")
    List<Menu> selectMenuByUserId(@Param("userId") Long userId);

}
