package me.teenyda.fruit.common.entity;

import android.text.format.DateFormat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (OrderPayment)实体类
 *
 * @author makejava
 * @since 2020-11-07 10:18:52
 */

@Data
public class OrderPayment implements Serializable {
    private static final long serialVersionUID = 950786359231003518L;

    private Integer orderPaymentId;

    private String orderNum;

    // 总金额
    private Double totalAmount;
    // 支付金额
    private Double payAmount;
    // 优惠金额
    private Double discountAmount;
    // 创建时间
    private Date creationTime;
    // 结束时间
    private Date endTime;
    // 支付状态0未付款 1已付款
    private Integer payStatus;
    private Integer payType;


}