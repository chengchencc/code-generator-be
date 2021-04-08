package com.ludan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: chengchen
 * @create: 2021-03-18 14:10
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ludan.common.remote"})
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
