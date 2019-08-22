package me.teenyda.mvp_template.model.login.base;

import android.content.Context;

import com.jaeger.library.StatusBarUtil;

import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.MvpActivity;
import me.teenyda.mvp_template.model.login.base.model.ILoginM;
import me.teenyda.mvp_template.model.login.base.presenter.LoginP;
import me.teenyda.mvp_template.model.login.base.view.ILoginV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class LoginAct extends MvpActivity<ILoginV, ILoginM, LoginP> implements ILoginV{


    @Override
    protected LoginP createPresenter() {
        return new LoginP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_login;
    }

    @Override
    protected void viewInitializer() {

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
