package sven.ws.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "sven.ws.web")
public class SvenWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvenWebApplication.class, args);
    }

}
