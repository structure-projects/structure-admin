package cn.structured.sa.manager.impl;

import cn.structure.common.exception.CommonException;
import cn.structured.sa.constants.StringConstant;
import cn.structured.sa.entity.Organization;
import cn.structured.sa.entity.User;
import cn.structured.sa.entity.UserRole;
import cn.structured.sa.manager.IOrganizationManager;
import cn.structured.sa.manager.IUserManager;
import cn.structured.sa.mapper.UserMapper;
import cn.structured.sa.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户manager实现
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager {

    private final UserRoleMapper userRoleMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final IOrganizationManager organizationManager;

    @Override
    public Long save(User user, List<Long> roleIds) {

        //生成用户加密后的密码
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

//        //生成域账号
//        Organization organization = organizationManager.getOrganizationByOrganizationId(user.getOrganizationId());
//        String domain = organization.getDomain();
//        String domainUsername = user.getUsername() + StringConstant.AT + domain;
//        user.setDomainUsername(domainUsername);

        //保存用户
        userMapper.insert(user);
        //获取用户ID
        Long userId = user.getId();

        //构建用户角色
        List<UserRole> userRoles = roleIds.stream()
                .map(roleId -> UserRole.builder()
                        .userId(userId)
                        .roleId(roleId)
                        .build())
                .collect(Collectors.toList());

        //判断用户角色是否为空
        if (!userRoles.isEmpty()) {
            //用户角色不为空时插入角色
            userRoleMapper.insertList(userRoles);
        }

        return userId;
    }


    @Override
    public User getById(Serializable id) {
        User user = userMapper.selectById(id);
        if (null == user) {
            //todo 抛出异常，用户不存在
            throw new CommonException("11111","22");
        }
        return user;
    }
}
