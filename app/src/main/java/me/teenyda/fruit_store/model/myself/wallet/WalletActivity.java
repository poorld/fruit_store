package me.teenyda.fruit_store.model.myself.wallet;

import android.content.Context;
import android.content.Intent;

import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.model.myself.wallet.presenter.WalletPresenter;
import me.teenyda.fruit_store.model.myself.wallet.view.IWalletView;

/**
 * author: teenyda
 * date: 2020/9/20
 * description:
 */
public class WalletActivity extends MvpActivity<IWalletView, WalletPresenter> implements IWalletView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected WalletPresenter createPresenter() {
        return new WalletPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_wallet;
    }

    @Override
    protected void viewInitializer() {
        setBack();
        setTitleShow(true, "钱包", false);
        setStatusBarTran(true, true);
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
