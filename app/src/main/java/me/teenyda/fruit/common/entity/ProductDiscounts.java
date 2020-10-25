package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (ProductDiscounts)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:54
 */
public class ProductDiscounts implements Serializable {
    private static final long serialVersionUID = 715522272173965833L;

    private Integer pdId;

    private Integer productId;

    private Integer discountsId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(Integer discountsId) {
        this.discountsId = discountsId;
    }

}