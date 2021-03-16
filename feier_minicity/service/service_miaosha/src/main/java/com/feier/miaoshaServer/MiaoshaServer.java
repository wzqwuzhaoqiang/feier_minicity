package com.feier.miaoshaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MiaoshaServer {
    public static void main(String[] args) {
        System.out.println("HelloWorld");
        SpringApplication.run(MiaoshaServer.class,args);
    }
}
