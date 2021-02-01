package sven.ws.web.security.po;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sven.common.model.User;

import java.util.Collection;
import java.util.List;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:55
 * @description：
 * @version:
 * @see
 */
@Setter
@Getter
public class SvenUser implements UserDetails {
    private Long id;

    private String username;

    private String password;

    /***和角色是多对多的关系*/
    private List<SvenRole> roles;

    private  Collection<? extends GrantedAuthority> grantedAuthority;

    /***
     * 正常情况下，角色和权限是两回事，
     * 所以我们还需要重写getAuthorities方法，将用户的角色和权限关联起来
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
