package cn.structured.admin.client;

import cn.structure.common.entity.ResResultVO;
import cn.structured.user.common.dto.user.RegisterPlatformUserDTO;
import cn.structured.user.common.dto.user.RestPasswordDTO;
import cn.structured.security.entity.StructureAuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author chuck
 * @version 2024/07/19 下午11:40
 * @since 1.8
 */
@FeignClient(value = "user-service", path = "/user")
public interface UserFeignClient {

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
    ResResultVO<StructureAuthUser> getUserByUsername(@Parameter(description = "用户名", example = "admin")
                                                     @RequestParam("username") String username);

    @PutMapping(value = "/open-api/enable/{userId}")
    ResResultVO<Void> enable(@Parameter(description = "用户ID", example = "1645717015337684992")
                             @PathVariable("userId") Long userId);


    @PutMapping(value = "/open-api/disable/{userId}")
    ResResultVO<Void> disable(@Parameter(description = "用户ID", example = "1645717015337684992")
                              @PathVariable("userId") Long userId);


    @DeleteMapping(value = "/open-api/{ids}")
    ResResultVO<Void> removeByIds(@Parameter(description = "用户ID", example = "1645717015337684992")
                                  @PathVariable Set<Long> ids);

}
