package io.yang.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.yang.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangyang
 * @date 2022/07/23 23:19
 * @description 商品服务Mapper接口
 **/
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 扣减商品库存
     *
     * @param count 需要扣减的商品数量
     * @param id    商品的id
     * @return 商品id
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}
