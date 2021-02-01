package sven.ws.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sven.service.iser.IUserService;
import sven.ws.web.base.BaseController;
import sven.ws.web.security.service.SvenUserDetailService;
import sven.ws.web.vo.HttpResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:52
 * @description：
 * @version:
 * @see
 */
@Slf4j
@RestController
public class LoginController extends BaseController {
    @Autowired
    private  AuthenticationManagerBuilder authenticationManagerBuilder;

    @RequestMapping("/login")
    public HttpResult login(String username, String password, HttpServletRequest request){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password))
            return HttpResult.no("账号信息错误");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        String session_id = request.getSession().getId();

        log.info("session_id:{}",session_id);

        return HttpResult.ok( SecurityContextHolder.getContext().getAuthentication().getPrincipal() ," 登录成功 !" );
    }
}
