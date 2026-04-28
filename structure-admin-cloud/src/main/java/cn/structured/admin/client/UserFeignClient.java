package cn.structured.admin.client;

import cn.structure.common.entity.ResResultVO;
import cn.structured.user.api.dto.user.AssigningRoleDTO;
import cn.structured.user.api.dto.user.RegisterPlatformUserDTO;
import cn.structured.user.api.dto.user.RestPasswordDTO;
import cn.structured.security.entity.StructureAuthUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author chuck
 * @version 2024/07/19 下午11:40
 * @since 1.8
 */
@FeignClient(value = "user-center")
public interface UserFeignClient {

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 用户权限
     */
    @GetMapping(value = "/open-api/getUserAuthorities/{userId}")
    ResResultVO<List<String>> getUserAuthorities(@ApiParam(value = "用户ID", example = "18888888888")
                                                 @PathVariable("userId") Long userId);

    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return 用户角色
     */
    @GetMapping(value = "/open-api/getUserRole/{userId}")
    ResResultVO<List<String>> getUserRole(@ApiParam(value = "用户ID", example = "18888888888")
                                          @PathVariable("userId") Long userId);


    @ApiOperation(value = "查询用户角色ids")
    @GetMapping(value = "/open-api/getUserRoleIds/{userId}")
    ResResultVO<List<Long>> getUserRoleIds(@ApiParam(value = "用户ID", example = "18888888888")
                                           @PathVariable("userId") Long userId);

    /**
     * 注册平台用户
     *
     * @param registerPlatformUser 注册平台DTO
     * @return Long
     */
    @PostMapping(value = "/open-api/register")
    ResResultVO<Long> registerPlatformUser(@RequestBody RegisterPlatformUserDTO registerPlatformUser);

    /**
     * 重置用户密码
     *
     * @param restPasswordDto 重置用户密码DTO
     */
    @PutMapping(value = "/open-api/resetPassword")
    ResResultVO<Void> resetPassword(@RequestBody RestPasswordDTO restPasswordDto);

    /**
     * 通过用户名查询用户详情
     *
     * @param username 用户名
     * @return StructureAuthUser
     */
    @GetMapping(value = "/open-api/getUserByUsername")
    ResResultVO<StructureAuthUser> getUserByUsername(@ApiParam(value = "用户名", example = "admin")
                                                     @RequestParam("username") String username);

    @PutMapping(value = "/open-api/assigningRole}")
    ResResultVO<Void> assigningRole(@RequestBody @Validated AssigningRoleDTO assigningRoleDto);


    @PutMapping(value = "/open-api/enable/{userId}")
    ResResultVO<Void> enable(@ApiParam(value = "用户ID", example = "1645717015337684992")
                             @PathVariable("userId") Long userId);


    @PutMapping(value = "/open-api/disable/{userId}")
    ResResultVO<Void> disable(@ApiParam(value = "用户ID", example = "1645717015337684992")
                              @PathVariable("userId") Long userId);


    @DeleteMapping(value = "/open-api/{ids}")
    ResResultVO<Void> removeByIds(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                  @PathVariable Set<Long> ids);

}
