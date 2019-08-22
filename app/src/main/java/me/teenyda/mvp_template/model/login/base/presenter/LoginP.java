package me.teenyda.mvp_template.model.login.base.presenter;

import me.teenyda.mvp_template.common.api.BaseObserver;
import me.teenyda.mvp_template.common.mvp.BasePresenter;
import me.teenyda.mvp_template.model.login.base.model.ILoginM;
import me.teenyda.mvp_template.model.login.base.view.ILoginV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class LoginP extends BasePresenter<ILoginV, ILoginM> {

    public void doLogin(String username, String password) {
        addDisposable(mApiServer.login(username, password), new BaseObserver(mView) {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

}
