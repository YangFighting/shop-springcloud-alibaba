package io.yang.shop.user.service.impl;

import io.yang.shop.bean.User;
import io.yang.shop.user.mapper.UserMapper;
import io.yang.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyang
 * @date 2022/07/22 23:26
 * @description 用户业务实现类
 **/

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}
