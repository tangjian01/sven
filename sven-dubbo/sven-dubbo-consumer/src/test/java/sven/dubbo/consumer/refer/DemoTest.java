package sven.dubbo.consumer.refer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sven.common.model.User;
import sven.dubbo.consumer.ConsumerApplication;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 14:34
 * @description：
 * @version:
 * @see
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class DemoTest {
    @Autowired
    private DubboService dubboService;
    @Test
    public void getUser(){
        User user = dubboService.getIPhoneService().getUser(1L);
        log.info("{}",user);
    }
}
