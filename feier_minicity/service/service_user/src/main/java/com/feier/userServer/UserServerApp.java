package com.feier.userServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.feier"})
public class UserServerApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApp.class,args);
    }
}
