package sven.ws.web.security.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import sven.common.code.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:35
 * @description：
 * @version:
 * @see
 */
@Component
public class LogoutSuccess implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String,String> map=new HashMap<>(2);
        map.put("code", ResultCode.LOGOUT_SUCCESS);
        map.put("msg", "登出成功");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
    }
}
