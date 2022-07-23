package io.yang.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangyang
 * @date 2022/07/23 23:32
 **/
@SpringBootApplication
@MapperScan(value = { "io.yang.shop.product.mapper" })
@EnableTransactionManagement(proxyTargetClass = true)
public class ProductStarter {
    public static void main(String[] args){
        SpringApplication.run(ProductStarter.class, args);
    }
}
