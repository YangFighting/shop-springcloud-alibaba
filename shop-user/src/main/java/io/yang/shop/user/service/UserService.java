package io.yang.shop.user.service;

import io.yang.shop.bean.User;

/**
 * @author zhangyang
 * @date 2022/07/22 23:24
 * @description 用户业务接口
 **/
public interface UserService {

    /**
     * 根据id获取用户信息
     * @param userId 用户id
     * @return 用户
     */
    User getUserById(Long userId);
}
