package me.teenyda.fruit.model.login.register;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.login.register.presenter.RegisterP;
import me.teenyda.fruit.model.login.register.view.IRegisterV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description: 注册
 */
public class RegisterAct extends MvpActivity<IRegisterV, RegisterP> implements IRegisterV {

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;

    @BindView(R.id.register_username_et)
    EditText register_username_et;
    @BindView(R.id.register_password_et)
    EditText register_password_et;
    @BindView(R.id.register_repassword_et)
    EditText register_repassword_et;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterAct.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                finish();
                break;
            case R.id.register:
                doRegister();
                break;
        }
    }

    private void doRegister() {
        String userName = register_username_et.getText().toString();
        String password = register_password_et.getText().toString();
        String rePassword = register_repassword_et.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)){
            showToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(rePassword)){
            showToast("请再次确认密码");
            return;
        }
        if (!password.equals(rePassword)) {
            showToast("密码不一致");
            return;
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        mPresenter.register(user);
    }


    @Override
    protected RegisterP createPresenter() {
        return new RegisterP();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_register;
    }

    @Override
    protected void initView() {
        setStatusBarTran(false, true);
        ButterKnife.bind(this);
    }

    @Override
    protected void requestData() {

    }


    @Override
    public Context getMContext() {
        return this;
    }


    @Override
    public void registerSuccess() {
        showToast("注册成功");
        finish();
    }
}
