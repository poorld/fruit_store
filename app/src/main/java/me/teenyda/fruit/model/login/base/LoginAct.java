package me.teenyda.fruit.model.login.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.login.base.presenter.LoginP;
import me.teenyda.fruit.model.login.base.view.ILoginV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class LoginAct extends MvpActivity<ILoginV, LoginP> implements ILoginV{

    private TextView login;
    private EditText login_username_et;
    private EditText login_password_et;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginAct.class);
        context.startActivity(intent);
    }

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
        setStatusBarTran(false, true);
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
    public Context getMContext() {
        return this;
    }

    @Override
    public void loginSuccess() {

    }
}
