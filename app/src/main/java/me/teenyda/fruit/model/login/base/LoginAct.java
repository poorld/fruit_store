package me.teenyda.fruit.model.login.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.FileCacheUtil;
import me.teenyda.fruit.common.utils.PermissionsUtil;
import me.teenyda.fruit.model.login.base.presenter.LoginP;
import me.teenyda.fruit.model.login.base.view.ILoginV;
import me.teenyda.fruit.model.login.register.RegisterAct;
import me.teenyda.fruit.model.main.MainActivity;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class LoginAct extends MvpActivity<ILoginV, LoginP> implements ILoginV,EasyPermissions.PermissionCallbacks {

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.login_username_et)
    EditText login_username_et;

    @BindView(R.id.login_password_et)
    EditText login_password_et;

    @BindView(R.id.to_register)
    TextView to_register;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginAct.class);
        context.startActivity(intent);
    }

    @Override
    protected LoginP createPresenter() {
        return new LoginP();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int setR_layout() {
        return R.layout.act_login;
    }

    @Override
    protected void initView() {
        setStatusBarTran(false, true);
        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = getUserName();
                String password = getPassword();
                if (TextUtils.isEmpty(userName)) {
                    showToast("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                User user = new User();
                user.setUsername(userName);
                user.setPassword(password);
                mPresenter.doLogin(user);
            }
        });
        to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterAct.startActivity(getMContext());
            }
        });
    }

    @Override
    protected void requestData() {
        PermissionsUtil.permission_storage(getMContext());
        User user = FileCacheUtil.getUser(getMContext());
        if (user != null) {
            MainActivity.startActivity(getMContext());
            finish();
        }
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
    public void loginSuccess(User user1) {
        FileCacheUtil.saveUser(getMContext(), user1);
        MainActivity.startActivity(getMContext());
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        User user = FileCacheUtil.getUser(getMContext());
        if (user != null) {
            MainActivity.startActivity(getMContext());
            finish();
        }
    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {

    }
}
