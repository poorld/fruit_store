package me.teenyda.fruit.model.myself.order.fragment.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public interface IOrderListView extends BaseView {

    public void setOrders(List<Order> orders);

    void takeDeliverySuccess();
}
