package io.yang.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.yang.shop.bean.User;
import io.yang.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyang
 * @date 2022/07/22 23:29
 * @description 用户接口
 **/
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public User getUser(@PathVariable("uid") Long uid) {
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}", JSONObject.toJSONString(user));

        return user;
    }


}
