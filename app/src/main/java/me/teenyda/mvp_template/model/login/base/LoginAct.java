package me.teenyda.mvp_template.model.login.base;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


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

    private TextView login;
    private EditText login_username_et;
    private EditText login_password_et;


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
        login = (TextView) $(R.id.login);
        login_username_et = (EditText) $(R.id.login_username_et);
        login_password_et = (EditText) $(R.id.login_password_et);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doLogin(getUserName(), getPassword());
            }
        });
    }

    @Override
    protected void doBuseness() {

    }

    private String getUserName() {
        return login_username_et.getText().toString();
    }

    private String getPassword() {
        return login_password_et.getText().toString();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSuccess() {

    }
}
