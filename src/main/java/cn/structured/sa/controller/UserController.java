package cn.structured.sa.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.jwt.entity.AuthUser;
import cn.structure.starter.jwt.interfaces.ITokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2022/5/10 22:27
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ITokenService tokenService;

    @GetMapping("/current")
    public ResResultVO getCurrentUserInfo(HttpServletRequest request) {
        String token = tokenService.getToken(request);
        AuthUser userInfo = tokenService.getUserInfoFromToken(token);
        return ResResultVO.success(userInfo.getId());
    }

    @GetMapping
    public ResResultVO get(@RequestParam Serializable id) {
        return ResResultVO.success(id);
    }

}
