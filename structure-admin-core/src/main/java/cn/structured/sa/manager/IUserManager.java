package cn.structured.sa.manager;

import cn.structured.sa.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * 用户Manager
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
public interface IUserManager {

    /**
     * 创建用户
     *
     * @param user    用户信息
     * @param roleIds 角色IDS
     * @return
     */
    Long save(User user, List<Long> roleIds);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    User getById(Serializable id);
}
