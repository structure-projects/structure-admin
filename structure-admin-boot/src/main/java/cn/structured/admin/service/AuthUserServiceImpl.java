package cn.structured.admin.service;

import cn.structured.admin.manager.IUserManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/11 14:23
 */
@Service
public class AuthUserServiceImpl implements UserDetailsService {

    @Resource
    private IUserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userManager.loadUserByUsername(username);
    }
}
