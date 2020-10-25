package me.teenyda.fruit.model.classify.settlement;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.view.popupview.PopupPayment;
import me.teenyda.fruit.model.classify.settlement.presenter.SettlementPresenter;
import me.teenyda.fruit.model.classify.settlement.view.ISettlementView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class SettlementActicity extends MvpActivity<ISettlementView, SettlementPresenter> {

    @BindView(R.id.ll_payment)
    LinearLayout ll_payment;

    private PopupPayment mPopupPayment;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettlementActicity.class);
        context.startActivity(intent);
    }

    @Override
    protected SettlementPresenter createPresenter() {
        return new SettlementPresenter();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_payment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_payment:
                mPopupPayment.show(view);
                break;

        }
    }

    @Override
    protected int setR_layout() {
        return R.layout.act_settlement;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mPopupPayment = new PopupPayment(getMContext());
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
