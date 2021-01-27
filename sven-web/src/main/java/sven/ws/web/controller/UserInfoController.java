package sven.ws.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sven.ws.web.vo.HttpResult;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/21 16:05
 * @description：
 * @version:
 * @see
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {


    @GetMapping("/userinfo")
    public HttpResult getUserInfo(Long id){
        return HttpResult.ok(null,"成功");
    }
}
