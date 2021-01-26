package sven.ws.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "sven.ws.web")
@MapperScan(basePackages = "sven.ws.dao.mapper")
public class SvenWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvenWebApplication.class, args);
    }

}
