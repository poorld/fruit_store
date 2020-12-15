package me.teenyda.fruit.model.cart.base.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public interface IShoppingCartView extends BaseView {

    void setCart(List<Order> orders);

    void cartOrderSuccess(OrderInfo orderInfo);
}
