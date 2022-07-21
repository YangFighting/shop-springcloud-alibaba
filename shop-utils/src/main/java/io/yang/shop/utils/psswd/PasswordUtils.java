package io.yang.shop.utils.psswd;

import io.yang.shop.utils.md5.Md5Hash;

/**
 * @author zhangyang
 * @date 2022/07/21 23:19
 * @description 密码工具类
 **/
public class PasswordUtils {
    public static String getPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return password;
        }

        for (int i = 0; i < 5; i++) {
            password = Md5Hash.md5Java(password);
        }
        return password;
    }

}

