package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (ProductTag)实体类
 * 产品标签
 * @author makejava
 * @since 2020-10-14 12:39:28
 */
public class ProductTag implements Serializable {
    private static final long serialVersionUID = 966252956752725054L;

    private Integer productId;

    private Integer tagId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}