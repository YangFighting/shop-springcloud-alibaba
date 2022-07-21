package io.yang.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.yang.shop.utils.id.SnowFlakeFactory;
import io.yang.shop.utils.psswd.PasswordUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangyang
 * @date 2022/07/21 22:59
 * @description 用户实体类
 **/
@Data
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6541647040633511284L;


    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public User() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        this.password = PasswordUtils.getPassword("123456");
    }
}
