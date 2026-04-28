package cn.structured.admin.manager;

import cn.structured.user.api.dto.user.RegisterPlatformUserDTO;
import cn.structured.security.entity.StructureAuthUser;

import java.util.List;
import java.util.Set;

/**
 * 用户Manager
 * @author chuck
 * @since 1.8
 */
public interface IUserManager {

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> getUserAuthorities(Long userId);

    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> getUserRole(Long userId);

    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return List<Long>
     */
    List<Long> getUserRoleIds(Long userId);

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
     * 分配角色
     *
     * @param roleIds 角色IDS
     * @param userId  用户ID
     */
    void assigningRole(List<Long> roleIds, Long userId);


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
