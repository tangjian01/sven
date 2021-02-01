package sven.ws.web.security.auth;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:40
 * @description：
 * @version:
 *  做权限验证 : URL权限拦截
 */
@Component
public class DoAuthCheck implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        //暂时不做权限验证
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        //暂时不做权限验证
        return true;
    }
}
