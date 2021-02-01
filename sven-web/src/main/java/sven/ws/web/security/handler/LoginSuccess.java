package sven.ws.web.security.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sven.common.code.ResultCode;
import sven.ws.web.security.po.SvenUser;
import sven.ws.web.vo.HttpResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:33
 * @description：
 * @version:
 *   登录成功处理器
 */
@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SvenUser user = (SvenUser) authentication.getPrincipal();
        log.info("用户 ：" + user.getUsername() + " 登录");
        Map<String,String> map=new HashMap<>(2);
        map.put("code", ResultCode.LOGIN_SUCCESS);
        map.put("msg", "登录成功");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
    }
}
