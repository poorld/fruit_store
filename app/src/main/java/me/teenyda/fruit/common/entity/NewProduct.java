package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (NewProduct)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:46
 */
public class NewProduct implements Serializable {
    private static final long serialVersionUID = 883210235693094896L;

    private Integer newProductId;

    private Integer productId;

    private String image;

    private Double price;

    private String desc;


    public Integer getNewProductId() {
        return newProductId;
    }

    public void setNewProductId(Integer newProductId) {
        this.newProductId = newProductId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}