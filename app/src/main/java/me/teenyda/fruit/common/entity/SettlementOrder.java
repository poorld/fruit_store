package me.teenyda.fruit.common.entity;

import java.util.Date;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/11/2
 * description:
 */
@Data
public class SettlementOrder {

    private String orderItemId;

    private String orderNum;

    private Integer quantity;

    private Integer userId;

    private OrderProduct product;

    @Data
    public class OrderProduct {
        private Integer productId;

        private String name;

        // 描述
        private String explain;

        // 封面图片
        private String defaultImg;

        private String updateTime;

        private String createTime;

        // 规格
        private Spec spec;
    }
}
