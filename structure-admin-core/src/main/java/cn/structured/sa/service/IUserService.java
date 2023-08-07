package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
public interface IUserService extends UserDetailsService, IBaseService<User> {

    /**
     * 更新用户
     *
     * @param user    用户信息
     * @param roleIds 用户角色ID
     */
    void updateById(User user, List<Long> roleIds);

    /**
     * 注册
     *
     * @param user    注册用户
     * @param roleIds 用户的角色ID
     * @return 返回用户ID
     */
    Long register(User user, List<Long> roleIds);

    /**
     * 更改密码
     *
     * @param userId      用户ID
     * @param oldPassword 旧的密码
     * @param newPassword 新的密码
     */
    void changePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     *
     * @param userId      用户ID
     * @param newPassword 密码
     */
    void resetPassword(Long userId, String newPassword);


    /**
     * 启用
     *
     * @param userId 用户ID
     */
    void enable(Long userId);

    /**
     * 停用
     *
     * @param userId 用户ID
     */
    void disable(Long userId);

    /**
     * 锁定用户
     *
     * @param userId 用户ID
     */
    void lock(Long userId);

    /**
     * 解锁用户
     *
     * @param userId 用户ID
     */
    void unlock(Long userId);

    /**
     * 获取用户菜单
     * @param userId userId
     * @return
     */
    List<Menu> getUserMenu(Long userId);
}
