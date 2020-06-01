package com.hpdc.iface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import util.IdWorker;
import util.JwtUtil;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@MapperScan("com.hpdc.iface.mapper")
public class IfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IfaceApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
