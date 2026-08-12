package cn.structured.admin.biz.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.common.dto.CaptchaDTO;
import cn.structured.admin.biz.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@Tag(name = "验证码控制器")
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @GetMapping("/generate")
    @Operation(summary = "生成验证码")
    public ResResultVO<CaptchaDTO> generateCaptcha() {
        CaptchaDTO captcha = captchaService.generateCaptcha();
        return ResultUtilSimpleImpl.success(captcha);
    }
}