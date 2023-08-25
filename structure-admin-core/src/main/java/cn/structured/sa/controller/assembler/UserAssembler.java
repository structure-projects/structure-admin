package cn.structured.sa.controller.assembler;

import cn.structured.sa.client.dto.user.CreateUserDTO;
import cn.structured.sa.client.dto.user.UpdateUserDTO;
import cn.structured.sa.client.dto.user.UserRegisterDTO;
import cn.structured.sa.client.vo.UserVO;
import cn.structured.sa.entity.Role;
import cn.structured.sa.entity.User;
import com.google.common.collect.Lists;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户装配器
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public class UserAssembler {

    /**
     * 私有构造为防止构建实例
     */
    private UserAssembler() {

    }

    /**
     * 用户装配器
     *
     * @param register 入参是一个用户注册DTO
     * @return 返回一个用户实体
     */
    public static User assembler(UserRegisterDTO register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setNickName(register.getNickName());
        user.setPhoto(register.getPhoto());
        user.setEmail(register.getEmail());
        user.setPhone(register.getPhone());
        return user;
    }

    /**
     * 用户装配器
     *
     * @param createUser 创建用户的DTO
     * @return 返回用户信息
     */
    public static User assembler(CreateUserDTO createUser) {
        User user = new User();
        user.setUsername(createUser.getUsername());
        user.setPassword(createUser.getPassword());
        user.setNickName(createUser.getNickName());
        user.setPhoto(createUser.getPhoto());
        user.setEmail(createUser.getEmail());
        user.setPhone(createUser.getPhone());
        return user;
    }

    /**
     * 修改用户实体装配为用户实体
     *
     * @param updateUser 修改用户实体
     * @return 用户实体PO对象
     */
    public static User assembler(UpdateUserDTO updateUser) {
        User user = new User();
        user.setId(updateUser.getId());
        user.setNickName(updateUser.getNickName());
        user.setPhoto(updateUser.getPhoto());
        user.setEmail(updateUser.getEmail());
        user.setPhone(updateUser.getPhone());
        return user;
    }

    /**
     * 用户实体装配为用户VO
     *
     * @param user 用户实体
     * @return 用户VO
     */
    public static UserVO assembler(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setNickName(user.getNickName());
        userVO.setPhoto(user.getPhoto());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setEnabled(user.getEnabled());
        userVO.setLocked(!user.getUnlocked());
        userVO.setRole(Optional
                .ofNullable(user.getRoleList())
                .orElse(Lists.newArrayList())
                .stream()
                .map(Role::getId)
                .collect(Collectors.toList()));
        userVO.setOperator(user.getOperator());
        userVO.setOperatorTime(user.getUpdateTime());
        return userVO;
    }
}
