package cn.structured.admin.manager;

import cn.structured.security.entity.StructureAuthUser;
import cn.structured.user.common.dto.OptionDTO;
import cn.structured.user.common.dto.user.RegisterPlatformUserDTO;
import cn.structured.user.application.service.IUserService;
import cn.structured.user.domain.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chuck
 * @since 1.8
 */
@Slf4j
@Component
public class UserManagerImpl implements IUserManager {

    @Resource
    private IUserService userService;

    @Override
    public List<String> getUserAuthorities(Long userId) {
        return userService.getUserAuthorities(userId);
    }

    @Override
    public List<String> getUserRole(Long userId) {
        return userService.getUserRole(userId).stream().map(OptionDTO::getValue).collect(Collectors.toList());
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return userService.getUserRole(userId).stream().map(OptionDTO::getId).collect(Collectors.toList());
    }

    @Override
    public Long registerPlatformUser(RegisterPlatformUserDTO registerPlatformUserDto) {
        return userService.registerPlatformUser(registerPlatformUserDto);
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        userService.resetPassword(userId, newPassword);
    }

    @Override
    public StructureAuthUser loadUserByUsername(String username) {
        UserEntity user = userService.loadUserByUserName(username);
        StructureAuthUser authUser = new StructureAuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setEnable(user.getEnabled());
        authUser.setUnlocked(user.getUnlocked());
        authUser.setUnexpired(user.getUnexpired());
        authUser.setCreateTime(user.getCreateTime());
        authUser.setUpdateTime(user.getUpdateTime());
        List<String> userAuthorities = userService.getUserAuthorities(user.getId());
        authUser.setAuthorities(userAuthorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
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
        userService.deleteById(userId);
    }

    @Override
    public void removeByIds(Set<Long> userIds) {
        userService.deleteByIds(new ArrayList<>(userIds));
    }

}
