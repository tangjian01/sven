package sven.ws.web;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sven.common.model.User;
import sven.service.config.RedisUtil;
import sven.service.iser.IUserService;

import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
class SvenWebApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Test
    void contextLoads() {
        List<User> list = userService.list();
        redisUtil.set("t","hello world");
        redisUtil.set("t2",list);
        Object t = redisUtil.get("t");
        Object t2 = redisUtil.get("t2");

        System.out.println(t);
        System.out.println(JSONObject.toJSONString(t2));
    }

}
