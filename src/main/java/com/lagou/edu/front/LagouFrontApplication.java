package com.lagou.edu.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * LagouFrontApplication
 *
 * @author xianhongle
 * @data 2022/1/11 10:44 下午
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.lagou.edu")
public class LagouFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(LagouFrontApplication.class, args);
    }
}
