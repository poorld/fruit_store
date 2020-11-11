package me.teenyda.fruit.model.login.register.presenter;

import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.login.register.view.IRegisterV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class RegisterP extends BaseRxPresenter<IRegisterV> {

    public void doLogin(String username, String password) {

    }

    public void register(User user) {
        addDisposable(mApi.register(user), new MyObserver<Boolean>(mContext) {
            @Override
            public void onSuccess(Boolean bool) {
                mView.registerSuccess();
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                mView.showToast(errorMsg);
            }
        });
    }

}
