package sven.ws.web.security.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @date ：Created in 2021/2/1 14:36
 * @description：
 * @version:
 *   没有权限得处理器
 */
@Component
public class NoAuthAccessHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Map<String,String> map=new HashMap<>(2);
        map.put("code", ResultCode.NO_AUTH);
        map.put("msg", e.getMessage());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
    }
}
