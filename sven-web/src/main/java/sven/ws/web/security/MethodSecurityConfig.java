package sven.ws.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import sven.ws.web.security.auth.DoAuthCheck;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 16:00
 * @description：
 * @version:
 * @see
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private DoAuthCheck doAuthCheck;

    @Autowired
    public void setCustomPermissionEvaluator(DoAuthCheck customPermissionEvaluator) {
        this.doAuthCheck = customPermissionEvaluator;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(doAuthCheck);
        return expressionHandler;
    }
}
