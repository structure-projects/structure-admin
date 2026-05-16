package cn.structured.admin.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.api.dto.CaptchaDTO;
import cn.structured.admin.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "验证码控制器")
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @GetMapping("/generate")
    @ApiOperation(value = "生成验证码")
    public ResResultVO<CaptchaDTO> generateCaptcha() {
        CaptchaDTO captcha = captchaService.generateCaptcha();
        return ResultUtilSimpleImpl.success(captcha);
    }
}