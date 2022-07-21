package io.yang.shop.utils.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * @author zhangyang
 * @date 2022/07/20 23:32
 * @description 结果数据
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {


    private static final long serialVersionUID = -4066126403002625792L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String codeMsg;


    /**
     * 返回数据
     */
    private T data;
}
