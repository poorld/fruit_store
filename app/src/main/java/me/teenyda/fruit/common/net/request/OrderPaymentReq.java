package me.teenyda.fruit.common.net.request;

import lombok.Data;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.entity.OrderPayment;

/**
 * author: teenyda
 * date: 2020/11/7
 * description: 订单支付
 */
@Data
public class OrderPaymentReq {
    // 订单信息
    private OrderInfo orderInfo;
    // 支付金额、优惠金额
    private OrderPayment orderPayment;
}
