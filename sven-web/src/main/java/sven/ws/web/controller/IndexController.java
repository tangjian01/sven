package sven.ws.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sven.common.model.User;
import sven.service.iser.IUserService;
import sven.ws.web.base.BaseController;
import sven.ws.web.vo.HttpResult;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author tj
 * @since 2021-01-27
 */
@RestController
public class IndexController extends BaseController {
    @GetMapping("/")
    public HttpResult getUserInfo(Long id){
        return HttpResult.ok("index","成功");
    }
}

