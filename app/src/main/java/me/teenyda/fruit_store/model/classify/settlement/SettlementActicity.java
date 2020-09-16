package me.teenyda.fruit_store.model.classify.settlement;

import android.content.Context;
import android.content.Intent;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.model.classify.settlement.presenter.SettlementPresenter;
import me.teenyda.fruit_store.model.classify.settlement.view.ISettlementView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class SettlementActicity extends MvpActivity<ISettlementView, SettlementPresenter> {

    @BindView(R.id.ll_payment)

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettlementActicity.class);
        context.startActivity(intent);
    }

    @Override
    protected SettlementPresenter createPresenter() {
        return new SettlementPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_settlement;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this);

        StatusBarUtil.setColor(this, getColor(R.color.ColorstatusBar));
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
