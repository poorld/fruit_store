package me.teenyda.fruit.model.cart.base.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.OrderItemDto;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.cart.base.view.IShoppingCartView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class ShoppingCartPresenter extends BaseRxPresenter<IShoppingCartView> {

    public void getCart(Integer userId) {
        addDisposable(mApi.getCart(userId), new MyObserver<List<Order>>(mContext) {
            @Override
            public void onSuccess(List<Order> orders) {
                mView.setCart(orders);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void settlementCart(List<OrderItemDto> orderItems) {
        addDisposable(mApi.cartOrder(orderItems), new MyObserver<OrderInfo>(mContext) {
            @Override
            public void onSuccess(OrderInfo orderInfo) {
                mView.cartOrderSuccess(orderInfo);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
