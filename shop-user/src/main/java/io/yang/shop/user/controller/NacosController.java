package io.yang.shop.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyang
 * @date 2022/08/07 12:13
 * @description 从Nacos中获取配置项
 **/
@Slf4j
@RestController
public class NacosController {

    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${author.name}")
    private String nacosAuthorName;


    @GetMapping("/nacos/test")
    public String nacosTest(){
        String authorName = context.getEnvironment().getProperty("author.name");
        log.info("获取到的作者姓名为：{}", authorName);
        return authorName;
    }

    @GetMapping("/nacos/name")
    public String nacosName(){
        log.info("从Nacos中获取到的作者的姓名为：{}", nacosAuthorName);
        return nacosAuthorName;
    }
}
