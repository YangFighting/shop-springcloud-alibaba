package io.yang.shop.params;

import lombok.Data;

/**
 * @author zhangyang03
 * @Description 订单参数
 * @create 2022-07-24 15:15
 */
@Data
public class OrderParams {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 购买的商品数量
     */
    private Integer count;

    public boolean isEmpty() {
        return (productId == null || productId <= 0) ||
                (userId == null || userId <= 0) ||
                (count == null || count <= 0);
    }

}
