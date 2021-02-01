package sven.ws.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import sven.ws.web.security.handler.LoginFail;
import sven.ws.web.security.handler.LoginSuccess;
import sven.ws.web.security.handler.LogoutSuccess;
import sven.ws.web.security.handler.NoAuthAccessHandler;
import sven.ws.web.security.store.DbAuthenticationProvider;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 12:09
 * @description：
 * @version:
 * (@link (https://www.cnblogs.com/zhoukebo/p/9674361.html))
 */
@Configuration
@EnableWebSecurity
public class SvenSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccess loginSuccess;
    @Autowired
    private LoginFail loginFail;
    @Autowired
    private LogoutSuccess logoutSuccess;
    @Autowired
    private NoAuthAccessHandler noAuthAccessHandler;
    @Autowired
    private  CorsFilter corsFilter;
    /***注入我们自己的登录逻辑验证器AuthenticationProvider*/
    @Autowired
    private DbAuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //login"不进行权限验证
                .antMatchers("/login","/logout","/favicon.ico").permitAll()
                .anyRequest().authenticated()   //其他的需要登陆后才能访问
                .and()
                .logout().logoutSuccessHandler(logoutSuccess)
                .logoutUrl("/logout").permitAll()
                .and()
                //配置没有权限的自定义处理类
                .exceptionHandling().accessDeniedHandler(noAuthAccessHandler)
                .and()
                .cors()//新加入
                .and()
                .csrf().disable();// 取消跨站请求伪造防护
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //这里可启用我们自己的登陆验证逻辑

//        auth.
//                inMemoryAuthentication()
//                .withUser("spring")
//                .password("{noop}123456").roles("USER");
        auth.authenticationProvider(authenticationProvider);
    }
}
