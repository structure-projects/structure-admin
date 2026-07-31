package cn.structured.admin.configuration;

import cn.structure.starter.jwt.interfaces.ITokenService;
import cn.structure.starter.jwt.interfaces.ITokenStore;
import cn.structure.starter.jwt.properties.JwtConfig;
import cn.structure.starter.jwt.service.InnerTokenStore;
import cn.structure.starter.jwt.service.JwtDefaultServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 安全配置。
 * <p>
 * 主动声明 {@link ITokenService} 和 {@link ITokenStore} Bean，
 * 绕过 {@code AutoJwtConfiguration} 中 {@code @Resource ITokenService}
 * 与 {@code @Bean ITokenService} 的循环依赖。
 * </p>
 *
 * @author chuck
 * @since 2.0.0
 */
@Configuration
public class JwtConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ITokenService tokenService(JwtConfig jwtConfig) {
        return new JwtDefaultServiceImpl(jwtConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public ITokenStore tokenStore(ITokenService tokenService) {
        return new InnerTokenStore(tokenService);
    }
}
