package me.teenyda.fruit.model.myself.wallet;

import android.content.Context;
import android.content.Intent;

import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.wallet.presenter.WalletPresenter;
import me.teenyda.fruit.model.myself.wallet.view.IWalletView;

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
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_wallet;
    }

    @Override
    protected void initView() {
        setBack();
        setTitleShow(true, "钱包", false);
        setStatusBarTran(true, true);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
