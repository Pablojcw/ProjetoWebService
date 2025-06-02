package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration .class}, scanBasePackages = "com.app")

public class IniciadorMain {
    public static void main(String[] args) {
        SpringApplication.run(IniciadorMain.class, args);
    }
}