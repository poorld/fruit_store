package me.teenyda.fruit.common.entity;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/11/9
 * description:
 */
@Data
public class OrderItemDto {
    private String orderItemId;

    private String orderNum;

    private Integer productId;

    private Double price;

    private Integer quantity;

    private Integer userId;

    private Integer specId;

    private OrderProductDto product;
}
