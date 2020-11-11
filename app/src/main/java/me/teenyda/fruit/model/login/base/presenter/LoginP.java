package me.teenyda.fruit.model.login.base.presenter;

import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.login.base.view.ILoginV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class LoginP extends BaseRxPresenter<ILoginV> {

    public void doLogin(User user) {
        addDisposable(mApi.login(user), new MyObserver<User>(mContext) {
            @Override
            public void onSuccess(User user1) {
                mView.loginSuccess(user1);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                mView.showToast(errorMsg);
            }
        });
    }

}
