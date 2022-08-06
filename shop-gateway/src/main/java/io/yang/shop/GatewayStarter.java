package io.yang.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangyang
 * @date 2022/08/06 15:20
 * @description 服务网关启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStarter {
    public static void main(String[] args) {
        SpringApplication.run(GatewayStarter.class, args);
    }
}
