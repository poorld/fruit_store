package me.teenyda.fruit.model.classify.payment.presenter;

import me.teenyda.fruit.common.entity.OrderPayment;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.classify.payment.view.IPaymentView;

/**
 * author: teenyda
 * date: 2020/11/7
 * description:
 */
public class PaymentPresenter extends BaseRxPresenter<IPaymentView> {

    public void getOrderPayment(String orderNum) {
        addDisposable(mApi.getOrderPayment(orderNum), new MyObserver<OrderPayment>(mContext) {
            @Override
            public void onSuccess(OrderPayment orderPayment) {
                mView.setOrderPayment(orderPayment);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void pay(String orderNum) {
        addDisposable(mApi.pay(orderNum), new MyObserver<OrderPayment>(mContext) {
            @Override
            public void onSuccess(OrderPayment orderPayment) {
                mView.paySuccess(orderPayment);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                mView.showToast(errorMsg);
            }
        });
    }
    public void finish(String orderNum) {
        addDisposable(mApi.finish(orderNum), new MyObserver<Boolean>(mContext) {
            @Override
            public void onSuccess(Boolean res) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

}
