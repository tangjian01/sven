package sven.ws.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sven.ws.dao.model.User;
import sven.ws.web.service.UserService;
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
    @Autowired
    private UserService service;

    @GetMapping("/userinfo")
    public HttpResult getUserInfo(Long id){
        User users = service.getBaseMapper().selectById(1L);
        return HttpResult.ok(users,"成功");
    }
}
