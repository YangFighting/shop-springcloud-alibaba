package io.yang.shop.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangyang03
 * @Description RestTemplate 配置类
 * @create 2022-07-24 15:41
 */
@Configuration
public class LoadBalanceConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
