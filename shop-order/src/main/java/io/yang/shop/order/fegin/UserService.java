package io.yang.shop.order.fegin;

import io.yang.shop.bean.User;
import io.yang.shop.order.fegin.fallback.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangyang
 * @date 2022/07/25 23:00
 * @description 调用用户微服务的接口
 **/
@FeignClient(value = "server-user", fallback = UserServiceFallBack.class)
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
