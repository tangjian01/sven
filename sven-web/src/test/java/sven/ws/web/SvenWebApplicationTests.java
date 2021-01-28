package sven.ws.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sven.service.config.StringRedisUtil;
import sven.service.iser.IUserService;

@SpringBootTest
@ActiveProfiles("dev")
class SvenWebApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisUtil stringRedisUtil;
    @Test
    void contextLoads() {
        userService.list();
        stringRedisUtil.set("t","1");
    }

}
