package io.yang.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.yang.shop.utils.id.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyang
 * @date 2022/07/21 23:39
 * @description 订单明细
 **/
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 3240622171596096978L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;
    @TableField("t_order_id")
    private Long orderId;
    /**
     * 商品id
     */
    @TableField("t_pro_id")
    private Long proId;
    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;
    /**
     * 商品价格（单价）
     * }
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;
    /**
     * 购买数量
     */
    @TableField("t_number")
    private Integer number;

    public OrderItem() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}