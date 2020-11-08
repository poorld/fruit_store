package me.teenyda.fruit.model.classify.payment.view;

import me.teenyda.fruit.common.entity.OrderPayment;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/11/7
 * description:
 */
public interface IPaymentView extends BaseView {

    void setOrderPayment(OrderPayment orderPayment);

    void paySuccess(OrderPayment orderPayment);
}
