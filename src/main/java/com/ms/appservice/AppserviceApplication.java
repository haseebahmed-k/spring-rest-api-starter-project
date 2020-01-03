package com.ms.appservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.ms.appservice")
@Slf4j
public class AppserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppserviceApplication.class, args);
    }
}