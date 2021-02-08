package sven.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 13:54
 * @description：
 * @version:
 * @see
 */
@SpringBootApplication(scanBasePackages = {"sven.dubbo.consumer","sven.service"})
@MapperScan(basePackages = "sven.ws.dao.mapper")
@EnableDubboConfiguration
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConsumerApplication.class);
        application.run(args);
    }
}
