package me.teenyda.fruit.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (OrderPayment)实体类
 *
 * @author makejava
 * @since 2020-11-07 10:18:52
 */
public class OrderPayment implements Serializable {
    private static final long serialVersionUID = 950786359231003518L;

    private Integer orderPaymentId;

    private String orderNum;

    private Double totalAmount;

    private Double payAmount;

    private Double discountAmount;

    private Date creationTime;

    private Date endTime;


    public Integer getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(Integer orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}