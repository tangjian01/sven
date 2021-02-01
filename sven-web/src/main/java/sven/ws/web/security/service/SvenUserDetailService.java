package sven.ws.web.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sven.common.exception.SvenException;
import sven.common.model.User;
import sven.service.iser.IUserService;
import sven.ws.web.security.po.SvenUser;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 15:12
 * @description：
 * @version:
 * @see
 */
@Component
@Slf4j
public class SvenUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService iUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始登录 username = {}",username);
        User dbuser = iUserService.getByUserName(username);

        if(dbuser == null){
            throw new SvenException("用户不存在或密码错误");
        }

        SvenUser svenUser = new SvenUser();
        svenUser.setUsername( dbuser.getName() );
        svenUser.setId( dbuser.getId() );
        svenUser.setPassword(dbuser.getPassword());

        //查询角色、资源权限等

        return svenUser ;
    }
}
