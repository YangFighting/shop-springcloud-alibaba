package io.yang.shop.product.service.impl;

import io.yang.shop.bean.Product;
import io.yang.shop.product.mapper.ProductMapper;
import io.yang.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyang
 * @date 2022/07/23 23:28
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return productMapper.updateProductStockById(count, id);
    }
}
