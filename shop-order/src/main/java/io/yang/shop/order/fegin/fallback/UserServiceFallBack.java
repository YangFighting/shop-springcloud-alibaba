package io.yang.shop.order.fegin.fallback;

import io.yang.shop.bean.User;
import io.yang.shop.order.fegin.UserService;
import org.springframework.stereotype.Component;

/**
 * @author zhangyang
 * @date 2022/08/06 13:50
 **/
@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
