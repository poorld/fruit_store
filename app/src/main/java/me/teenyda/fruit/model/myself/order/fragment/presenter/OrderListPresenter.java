package me.teenyda.fruit.model.myself.order.fragment.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.myself.order.fragment.view.IOrderListView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class OrderListPresenter extends BaseRxPresenter<IOrderListView> {

    public void getOrders(Integer userId) {
        addDisposable(mApi.getOrders(userId), new MyObserver<List<Order>>(mContext) {
            @Override
            public void onSuccess(List<Order> orders) {
                mView.setOrders(orders);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getOrders(Integer userId, Integer status) {
        addDisposable(mApi.getOrdersByStatus(userId, status), new MyObserver<List<Order>>(mContext) {
            @Override
            public void onSuccess(List<Order> orders) {
                mView.setOrders(orders);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    /**
     * 确认收货
     * @param orderNum
     */
    public void orderComplete(String orderNum) {
        addDisposable(mApi.complete(orderNum), new MyObserver<OrderInfo>(mContext) {
            @Override
            public void onSuccess(OrderInfo orderInfo) {
                mView.takeDeliverySuccess();
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
