package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:50
 */
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -17799490146321986L;

    private String orderItemId;

    private String orderNum;

    private Integer productId;

    private Double price;

    private Integer quantity;

    private Integer userId;

    private Integer specId;


    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

}