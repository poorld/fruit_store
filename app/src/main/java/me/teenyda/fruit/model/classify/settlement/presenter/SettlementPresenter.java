package me.teenyda.fruit.model.classify.settlement.presenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import me.teenyda.fruit.common.api.BaseResponse;
import me.teenyda.fruit.common.entity.Discounts;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.entity.Wallet;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.common.mvp.RxHelper;
import me.teenyda.fruit.model.classify.settlement.view.ISettlementView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class SettlementPresenter extends BaseRxPresenter<ISettlementView> {

    public void getOrder(String orderNum) {
        addDisposable(mApi.getOrder(orderNum), new MyObserver<SettlementOrder>(mContext) {
            @Override
            public void onSuccess(SettlementOrder settlementOrder) {
                mView.setOrderItem(settlementOrder);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getUserInfo(int userId) {
        addDisposable(mApi.getUser(userId), new MyObserver<User>(mContext) {
            @Override
            public void onSuccess(User user) {
                mView.setUserInfo(user);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getDiscount(boolean isMember) {

        addDisposable(isMember ? mApi.getMemberDiscounts() : mApi.getUserDiscounts(),
            new MyObserver<List<Discounts>>(mContext) {
                @Override
                public void onSuccess(List<Discounts> discountsList) {
                    mView.setDiscount(discountsList);
                }

                @Override
                public void onFailure(Throwable e, String errorMsg) {

                }
            });
    }

    public void getWallet(Integer userId) {
        addDisposable(mApi.getWallet(userId), new MyObserver<Wallet>(mContext) {
            @Override
            public void onSuccess(Wallet wallet) {
                mView.setWallet(wallet);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
