package io.yang.shop.product.service;

import io.yang.shop.bean.Product;

/**
 * @author zhangyang
 * @date 2022/07/23 23:25
 **/
public interface ProductService {
    /**
     * 根据商品id获取商品信息
     *
     * @param pid 商品id
     * @return 商品信息
     */
    Product getProductById(Long pid);

    /**
     * 扣减商品库存
     *
     * @param count 需要扣减的商品数量
     * @param id 商品的id
     * @return 商品id
     */
    int updateProductStockById(Integer count, Long id);
}
