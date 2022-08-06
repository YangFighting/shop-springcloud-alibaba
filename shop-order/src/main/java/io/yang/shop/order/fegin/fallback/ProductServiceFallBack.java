package io.yang.shop.order.fegin.fallback;

import io.yang.shop.bean.Product;
import io.yang.shop.order.fegin.ProductService;
import io.yang.shop.utils.resp.Result;
import org.springframework.stereotype.Component;

/**
 * @author zhangyang
 * @date 2022/08/06 13:52
 **/
@Component
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("触发了容错逻辑");
        return result;
    }
}
