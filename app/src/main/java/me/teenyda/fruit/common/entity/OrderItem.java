package me.teenyda.fruit.common.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:50
 */
@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -17799490146321986L;

    private String orderItemId;

    private String orderNum;

    private Integer productId;

    private Double price;

    private Integer quantity;

    private Integer userId;

    private Integer specId;

    private Product product;

}