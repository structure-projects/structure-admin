package cn.structured.sa.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.structure.common.constant.AuthConstant;
import cn.structure.common.exception.CommonException;
import cn.structure.starter.oauth.common.entity.AuthUser;
import cn.structure.starter.oauth.common.enums.LoginErrCodeEnum;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.*;
import cn.structured.sa.manager.IUserManager;
import cn.structured.sa.mapper.*;
import cn.structured.sa.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户service实现类
 * <p>
 * 推荐遵守开闭原则对修改关闭新增开放,如果业务上有修改需求推荐新增实现，有重叠功能可以继承UserServiceImpl这实现并对方法进行重写
 * </p>
 *
 * @author cqliut
 * @version 2023.0621
 * @since 1.0.1
 */
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DeptRoleMapper deptRoleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IUserManager userManager;

    /**
     * 用户登录实现逻辑
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 不存在的用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // todo 更改用户登录逻辑 一个用户对应多个组织，支持多种认证方式和三方认证方式 ， 如果三方认证成功则颁发token 即可或者使用三方的token 看签约方式
        //获取组织ID

        //通过仓库查询用户信息（登录权限）
        LambdaQueryWrapper<User> userQuery;
        //如果存在组织信息
        //使用用户名+组织ID查询用户
        userQuery = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);

        User user = userMapper.selectOne(userQuery);
        if (null == user) {
            //不存在用户则返回错误信息
            throw new UsernameNotFoundException(LoginErrCodeEnum.USER_PASSWORD_ERR.getMsg());

        }
        //用户ID
        Long userId = user.getId();

        //构建扩展存储信息
        Map<String, String> extInfo = Maps.newHashMap();

        //用户的角色ID
        List<Long> userRoleId = userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        //查询用户的部门（数据权限）
        Employee employee = employeeMapper.selectById(userId);

        //如果存在职工信息则需要查询职务和部门和部门权限
        if (null != employee) {
            Long deptId = employee.getDeptId();

            //查询部门信息
            Dept dept = deptMapper.selectById(deptId);
            //查询用户的职务编码  （审批权限）
            String duty = employee.getDuty();

            //查询用户权限（功能权限）
            List<Long> deptRoleId = deptRoleMapper.selectList(Wrappers.<DeptRole>lambdaQuery().eq(DeptRole::getDeptId, deptId))
                    .stream()
                    .map(DeptRole::getRoleId)
                    .collect(Collectors.toList());

            //将两种角色汇聚
            userRoleId.addAll(deptRoleId);

            //赋值扩展属性
            extInfo.put(AuthConstant.DUTY, duty);
            extInfo.put(AuthConstant.DEPT_ID, deptId.toString());
            extInfo.put(AuthConstant.DEPT_PIDS, dept.getPids());
            extInfo.put(AuthConstant.DEPT_PID, dept.getPid().toString());
        }

        //使用据说去查询功能权限
        List<Menu> menus = roleMapper.selectMenuByRole(userRoleId);
        //构建成权限集合
        Collection<GrantedAuthority> authorities = menus.stream()
                .map(menu -> new SimpleGrantedAuthority(menu.getCode()))
                .collect(Collectors.toList());

        //转换成认证用户信息
        AuthUser authUser = new AuthUser();
        authUser.setId(userId);
        authUser.setUsername(username);
        authUser.setPassword(user.getPassword());
        authUser.setExtInfo(extInfo);
        authUser.setAuthorities(authorities);
        authUser.setUnlocked(user.getUnlocked());
        authUser.setEnable(user.getEnabled());
        authUser.setUnexpired(Boolean.TRUE);
        return authUser;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(User user, List<Long> roleIds) {

        //删除原有的角色
        userRoleMapper.delete(Wrappers.<UserRole>lambdaQuery()
                .eq(UserRole::getUserId, user.getId()));

        //构建用户角色
        List<UserRole> userRoleList = roleIds.stream()
                .map(roleId -> UserRole.builder()
                        .roleId(roleId)
                        .userId(user.getId())
                        .build())
                .collect(Collectors.toList());

        //判断用户角色不为空则插入新的角色
        if (!userRoleList.isEmpty()) {
            //插入新的角色
            userRoleMapper.insertList(userRoleList);
        }
        this.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long register(User user, List<Long> roleIds) {
        return userManager.save(user, roleIds);
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        //查询用户信息
        User user = this.getById(userId);
        String password = user.getPassword();
        //验证用户输入密码和数据库中的密码是否匹配
        boolean matches = passwordEncoder.matches(oldPassword, password);

        //验证密码是否正确
        if (!matches) {
            //todo 密码验证失败后提示错误信息！
            throw new CommonException();
        }
        //调用重置密码方法重置密码
        resetPassword(userId, newPassword);
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        String password = passwordEncoder.encode(newPassword);
        User user = new User();
        user.setId(userId);
        user.setPassword(password);
        userMapper.updateById(user);
    }

    @Override
    public void enable(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setEnabled(true);
        userMapper.updateById(user);
    }

    @Override
    public void disable(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setEnabled(false);
        userMapper.updateById(user);
    }

    @Override
    public void lock(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setUnlocked(false);
        userMapper.updateById(user);
    }

    @Override
    public void unlock(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setUnlocked(true);
        userMapper.updateById(user);
    }

    @Override
    public List<Menu> getUserMenu(Long userId) {
        return userMapper.selectMenuByUserId(userId);
    }

    @Override
    public User getById(Serializable id) {
        return userManager.getById(id);
    }
}
