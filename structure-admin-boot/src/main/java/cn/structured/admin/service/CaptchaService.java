package cn.structured.admin.service;

import cn.structured.admin.api.dto.CaptchaDTO;

/**
 * 验证码服务接口
 */
public interface CaptchaService {

    /**
     * 生成验证码
     * @return 验证码信息
     */
    CaptchaDTO generateCaptcha();

    /**
     * 验证验证码
     * @param captchaId 验证码ID
     * @param code 用户输入的验证码
     * @return 是否验证通过
     */
    boolean validateCaptcha(String captchaId, String code);

    /**
     * 删除验证码
     * @param captchaId 验证码ID
     */
    void removeCaptcha(String captchaId);
}