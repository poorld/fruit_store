package me.teenyda.fruit.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (SnapUp)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:56
 */
public class SnapUp implements Serializable {
    private static final long serialVersionUID = 501425714160282893L;

    private Integer snapUpId;

    private Integer productId;

    private Double price;

    private Date createTime;

    private Date endTime;


    public Integer getSnapUpId() {
        return snapUpId;
    }

    public void setSnapUpId(Integer snapUpId) {
        this.snapUpId = snapUpId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}