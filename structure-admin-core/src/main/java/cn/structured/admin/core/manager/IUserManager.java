package cn.structured.admin.core.manager;

import cn.structured.user.common.dto.user.RegisterPlatformUserDTO;
import cn.structured.security.entity.StructureAuthUser;

import java.util.Set;

/**
 * 用户Manager
 * @author chuck
 * @since 1.8
 */
public interface IUserManager {

    /**
     * 注册平台用户
     *
     * @param registerPlatformUserDto 注册平台DTO
     * @return Long
     */
    Long registerPlatformUser(RegisterPlatformUserDTO registerPlatformUserDto);

    /**
     * 重置用户密码
     *
     * @param userId userId
     */
    void resetPassword(Long userId, String newPassword);

    /**
     * 通过用户名查询用户详情
     *
     * @param username 用户名
     * @return StructureAuthUser
     */
    StructureAuthUser loadUserByUsername(String username);

    /**
     * 启用用户
     *
     * @param userId 用户ID
     */
    void enable(Long userId);

    /**
     * 停用用户
     *
     * @param userId 用户ID
     */
    void disable(Long userId);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    void removeById(Long userId);

    /**
     * 删除用户
     *
     * @param userIds ids
     */
    void removeByIds(Set<Long> userIds);

}
