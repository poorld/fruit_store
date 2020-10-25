package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (Banner)实体类
 *
 * @author makejava
 * @since 2020-10-09 16:51:57
 */
public class Banner implements Serializable {
    private static final long serialVersionUID = -80274809721458908L;

    private Integer bannerId;

    private Integer productId;

    private String image;

    private String desc;


    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}