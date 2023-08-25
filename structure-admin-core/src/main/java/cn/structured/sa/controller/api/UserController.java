package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.user.*;
import cn.structured.sa.client.vo.CurrentUserVO;
import cn.structured.sa.client.vo.UserMenuVO;
import cn.structured.sa.client.vo.UserVO;
import cn.structured.sa.controller.assembler.MenuAssembler;
import cn.structured.sa.controller.assembler.RoleAssembler;
import cn.structured.sa.controller.assembler.UserAssembler;
import cn.structured.sa.entity.Employee;
import cn.structured.sa.entity.Menu;
import cn.structured.sa.entity.Role;
import cn.structured.sa.entity.User;
import cn.structured.sa.group.DetailsGroup;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.mapper.EmployeeMapper;
import cn.structured.sa.mapper.UserRoleMapper;
import cn.structured.sa.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户模块
 * todo 对管理接口添加管理角色 全局添加使用状态对已经启用的角色不可以删除操作只能禁用，并验证所有的禁用数据是否符合预期结果
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final EmployeeMapper employeeMapper;

    private final UserRoleMapper userRoleMapper;

    /**
     * 注册需要有按钮定义是否开用用户注册
     *
     * @param userRegister 用户注册数据传输对象
     * @return 返回注册的结果 {@link ResResultVO} code = SUCCESS为注册成功 失败则是业务上的错误码！
     */
    @ApiOperation(value = "注册用户")
    @PostMapping(value = "/register")
    public ResResultVO<Long> register(@RequestBody @Validated UserRegisterDTO userRegister) {
        Long userId = userService.register(UserAssembler.assembler(userRegister), Lists.newArrayList());
        return ResultUtilSimpleImpl.success(userId);
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping(value = "/current")
    public ResResultVO<CurrentUserVO> current() {
        //获取当前用户ID
        Long userId = UserUtil.getUserId();
        //获取当前用户信息
        User user = userService.getById(userId);
        //获取当前用户的职工信息
        Employee employee = employeeMapper.selectById(userId);
        //获取当前员工的职工信息
        List<Role> roles = userRoleMapper.selectRoleByUserId(userId);
        CurrentUserVO currentUser = new CurrentUserVO();
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setNickName(user.getNickName());
        currentUser.setPhoto(user.getPhoto());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        currentUser.setRoles(Optional
                .ofNullable(roles)
                .orElse(new ArrayList<>())
                .stream()
                .map(RoleAssembler::assemblerOption)
                .collect(Collectors.toList()));
        if (null != employee) {
            currentUser.setDeptId(employee.getDept());
            currentUser.setDuty(employee.getDuty());
        }
        return ResultUtilSimpleImpl.success(currentUser);
    }

    @ApiOperation(value = "更改个人信息")
    @PutMapping(value = "/changeProfile")
    public ResResultVO<Void> changeProfile(@RequestBody @Validated ChangeProfileDTO changeProfile) {
        Long userId = UserUtil.getUserId();
        User user = new User();
        user.setId(userId);
        user.setNickName(changeProfile.getNickName());
        user.setPhoto(changeProfile.getPhoto());
        user.setEmail(changeProfile.getEmail());
        user.setPhone(changeProfile.getPhone());
        userService.updateById(user);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "更改密码")
    @PutMapping(value = "/changePassword")
    public ResResultVO<Void> changePassword(@RequestBody @Validated ChangePasswordDTO changePassword) {
        userService.changePassword(changePassword.getId(), changePassword.getOldPassword(), changePassword.getNewPassword());
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateUserDTO createUser) {
        Long userId = userService.register(UserAssembler.assembler(createUser), createUser.getRole());
        return ResultUtilSimpleImpl.success(userId);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateUserDTO updateUser) {
        userService.updateById(UserAssembler.assembler(updateUser), updateUser.getRole());
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "用户分页列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<UserVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                             @PathVariable("page") Long page,
                                             @ApiParam(value = "页大小", required = true, example = "20")
                                             @PathVariable("pageSize") Long pageSize,
                                             SearchUserDTO searchUser) {
        //构建数据实体
        User user = new User();
        user.setEnabled(searchUser.getEnabled());
        if (null != searchUser.getLocked()) {
            user.setUnlocked(!searchUser.getLocked());
        }
        user.setUsername(searchUser.getUsername());
        user.setNickName(searchUser.getNickName());
        user.setPhone(searchUser.getPhone());
        //构建查询参数
        QueryJoinPageListWrapper<User> queryWrapper = new QueryJoinPageListWrapper<>(user);
        queryWrapper.setIsJoin(true);
        queryWrapper.addColumn();
        queryWrapper.setJoinGroup(SearchGroup.class);
        IPage<User> userPage = userService.page(new Page<>(page, pageSize), queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(userPage, UserAssembler::assembler));
    }

    @ApiOperation(value = "获取用户详情")
    @GetMapping(value = "/get")
    public ResResultVO<UserVO> get(
            @ApiParam(value = "用户ID", required = true, example = "20") Long userId) {
        //构建数据实体
        User user = new User();
        user.setId(userId);
        //构建查询参数
        QueryJoinPageListWrapper<User> queryWrapper = new QueryJoinPageListWrapper<>(user);
        queryWrapper.setIsJoin(true);
        queryWrapper.addColumn();
        queryWrapper.setJoinGroup(DetailsGroup.class);
        return ResultUtilSimpleImpl.success(userService.list(queryWrapper)
                .stream()
                .findFirst().map(UserAssembler::assembler)
                .orElse(new UserVO()));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{userId}")
    public ResResultVO<Void> remove(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                    @PathVariable("userId") Long userId) {
        userService.removeById(userId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{userId}")
    public ResResultVO<Void> enable(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                    @PathVariable("userId") Long userId) {
        userService.enable(userId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{userId}")
    public ResResultVO<Void> disable(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                     @PathVariable("userId") Long userId) {
        userService.disable(userId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "锁定")
    @PutMapping(value = "/lock/{userId}")
    public ResResultVO<Void> lock(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                  @PathVariable("userId") Long userId) {
        userService.lock(userId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "解锁")
    @PutMapping(value = "/unlock/{userId}")
    public ResResultVO<Void> unlock(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                    @PathVariable("userId") Long userId) {
        userService.unlock(userId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "重置用户密码")
    @PutMapping(value = "/resetPassword")
    public ResResultVO<Void> resetPassword(@RequestBody @Validated ResetPasswordDTO resetPassword) {
        userService.resetPassword(resetPassword.getId(), resetPassword.getNewPassword());
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "菜单列表", notes = "当前用户菜单权限树 TREE结构")
    @GetMapping(value = "/getUserMenu")
    public ResResultVO<List<UserMenuVO>> getUserMenu() {
        //查询到用户所有的权限列表
        List<Menu> userMenu = userService.getUserMenu(UserUtil.getUserId());
        return ResultUtilSimpleImpl.success(MenuAssembler.assemblerUserMenu(userMenu));
    }

}
