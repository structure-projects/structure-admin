package cn.structured.admin.manager;

import cn.structured.admin.client.UserFeignClient;
import cn.structured.oauth.user.api.dto.user.AssigningRoleDto;
import cn.structured.oauth.user.api.dto.user.RegisterPlatformUserDto;
import cn.structured.oauth.user.api.dto.user.RestPasswordDto;
import cn.structured.security.entity.StructureAuthUser;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author chuck
 * @since 1.8
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager {

    private final UserFeignClient userFeignClient;

    @Override
    public List<String> getUserAuthorities(Long userId) {
        return userFeignClient.getUserAuthorities(userId).getData();
    }

    @Override
    public List<String> getUserRole(Long userId) {
        return userFeignClient.getUserRole(userId).getData();
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return userFeignClient.getUserRoleIds(userId).getData();
    }

    @Override
    public Long registerPlatformUser(RegisterPlatformUserDto registerPlatformUserDto) {
        return userFeignClient.registerPlatformUser(registerPlatformUserDto).getData();
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        userFeignClient.resetPassword(new RestPasswordDto() {{
            this.setUserId(userId);
            this.setPassword(newPassword);
        }});
    }

    @Override
    public StructureAuthUser loadUserByUsername(String username) {
        return userFeignClient.getUserByUsername(username).getData();
    }

    @Override
    public void assigningRole(List<Long> roleIds, Long userId) {
        userFeignClient.assigningRole(new AssigningRoleDto() {{
            setUserId(userId);
            setRoleIds(roleIds);
        }});
    }

    @Override
    public void enable(Long userId) {
        userFeignClient.enable(userId);
    }

    @Override
    public void disable(Long userId) {
        userFeignClient.disable(userId);
    }

    @Override
    public void removeById(Long userId) {
        Set<Long> ids = Sets.newConcurrentHashSet();
        ids.add(userId);
        userFeignClient.removeByIds(ids);
    }

    @Override
    public void removeByIds(Set<Long> userIds) {
        userFeignClient.removeByIds(userIds);
    }


}
