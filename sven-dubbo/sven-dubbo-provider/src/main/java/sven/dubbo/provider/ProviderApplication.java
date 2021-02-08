package sven.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 14:26
 * @description：
 * @version:
 * @see
 */
@SpringBootApplication(scanBasePackages = {"sven.dubbo.provider","sven.service"})
@MapperScan(basePackages = "sven.ws.dao.mapper")
@EnableDubboConfiguration
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProviderApplication.class);
        application.run(args);
    }
}
