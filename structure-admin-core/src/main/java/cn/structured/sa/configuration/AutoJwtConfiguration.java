package cn.structured.sa.configuration;

import cn.hutool.extra.spring.SpringUtil;
import cn.structure.starter.jwt.endpoint.LoginEndpoint;
import cn.structured.sa.service.IUserService;
import cn.structured.sa.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author cqliut
 * @version 2023.0704
 * @since 1.0.1
 */
@Configuration
@Import(SpringUtil.class)
public class AutoJwtConfiguration {

    /**
     * 装载JWT登录端口
     *
     * @return 返回登录端口类
     */
    @Bean
    public LoginEndpoint loginEndpoint() {
        return new LoginEndpoint();
    }

    /**
     * 用户业务实现类定义
     *
     * @return {@link IUserService}
     * @see org.springframework.security.core.userdetails.UserDetailsService
     */
    @Bean
    public IUserService userService() {
        return new UserServiceImpl();
    }
}
