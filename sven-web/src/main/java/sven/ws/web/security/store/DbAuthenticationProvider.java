package sven.ws.web.security.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sven.common.exception.SvenException;
import sven.ws.web.security.service.SvenUserDetailService;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:45
 * @description：
 * @version:
 *
 *   用户名 密码登录校验
 */
@Component
public class DbAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SvenUserDetailService svenUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        UserDetails userDetails = svenUserDetailService.loadUserByUsername(userName);

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new SvenException("用户名或密码不正确");
        }
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
