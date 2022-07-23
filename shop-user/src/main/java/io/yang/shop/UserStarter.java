package io.yang.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangyang
 * @date 2022/07/22 23:32
 * @description 启动用户服的类
 **/
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"io.yang.shop.user.mapper"})
@EnableConfigurationProperties
public class UserStarter {

    public static void main(String[] args) {
        SpringApplication.run(UserStarter.class, args);
    }
}
