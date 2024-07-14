package cn.structured.admin.manager;

import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import cn.structured.oauth.user.api.dto.user.RegisterPlatformUserDto;
import cn.structured.oauth.user.api.dto.user.UserDetailDto;
import cn.structured.oauth.user.entity.Role;
import cn.structured.oauth.user.entity.User;
import cn.structured.oauth.user.service.IUserService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chuck
 * @since 1.8
 */
@Slf4j
@Service
public class UserManagerImpl implements IUserManager {

    @Resource
    private IUserService userService;

    @Override
    public List<String> getUserAuthorities(Long userId) {
        return userService.getUserAuthorities(userId);
    }

    @Override
    public List<String> getUserRole(Long userId) {
        return userService.getUserRole(userId).stream().map(Role::getCode).collect(Collectors.toList());
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return userService.getUserRole(userId).stream().map(Role::getId).collect(Collectors.toList());
    }

    @Override
    public Long registerPlatformUser(RegisterPlatformUserDto registerPlatformUserDto) {
        return userService.registerPlatformUser(registerPlatformUserDto);
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        userService.resetPassword(userId, newPassword);
    }

    @Override
    public StructureAuthUser loadUserByUsername(String username) {
        User user = userService.loadUserByUserName(username);
        StructureAuthUser authUser = new StructureAuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setEnable(user.getEnabled());
        authUser.setUnlocked(user.getUnlocked());
        authUser.setUnexpired(user.getUnexpired());
        authUser.setCreateTime(user.getCreateTime());
        authUser.setUpdateTime(user.getUpdateTime());
        return authUser;
    }

    @Override
    public void assigningRole(List<Long> roleIds, Long userId) {
        userService.assigningRole(roleIds, userId);
    }

    @Override
    public void enable(Long userId) {
        userService.enable(userId);
    }

    @Override
    public void disable(Long userId) {
        userService.disable(userId);
    }

    @Override
    public void removeById(Long userId) {
        userService.removeById(userId);
    }

    @Override
    public void removeByIds(Set<Long> userIds) {
        userService.removeByIds(userIds);
    }

    @Override
    public UserDetailDto getUserDetailByUserId(Long userId) {
        return null;
    }
}
