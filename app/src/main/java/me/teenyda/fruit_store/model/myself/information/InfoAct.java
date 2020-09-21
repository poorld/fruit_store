package me.teenyda.fruit_store.model.myself.information;

import android.content.Context;
import android.content.Intent;

import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.model.myself.information.presenter.InfoPresenter;
import me.teenyda.fruit_store.model.myself.information.view.IInfoV;

/**
 * author: teenyda
 * date: 2020/9/21
 * description:
 */
public class InfoAct extends MvpActivity<IInfoV, InfoPresenter> implements IInfoV {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InfoAct.class);
        context.startActivity(intent);
    }

    @Override
    protected InfoPresenter createPresenter() {
        return new InfoPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_info;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this);
        setStatusBarTran(true, true);
        setBack();
        setTitle("个人信息");

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
