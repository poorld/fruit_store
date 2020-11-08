package me.teenyda.fruit.model.classify.payment;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.OrderPayment;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.classify.payment.presenter.PaymentPresenter;
import me.teenyda.fruit.model.classify.payment.view.IPaymentView;

/**
 * author: teenyda
 * date: 2020/11/7
 * description:
 */
public class PaymentAct extends MvpActivity<IPaymentView, PaymentPresenter> implements IPaymentView {

    @BindView(R.id.cv_countdown)
    CountdownView cv_countdown;

    public static void startActivity(Context context, String orderNumber) {
        Intent intent = new Intent(context, PaymentAct.class);
        intent.putExtra("orderNumber", orderNumber);
        context.startActivity(intent);
    }
    @Override
    protected PaymentPresenter createPresenter() {
        return new PaymentPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_payment;
    }

    @Override
    protected void initView() {
        setTitleShow(true, "支付", false);
        ButterKnife.bind(this);
        /*Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        c.add(Calendar.MINUTE, 10);
        long end = c.getTimeInMillis();
        cv_countdown.start(end - start);*/
    }

    @Override
    protected void requestData() {
        String orderNumber = getIntent().getStringExtra("orderNumber");
        mPresenter.getOrderPayment(orderNumber);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setOrderPayment(OrderPayment orderPayment) {
        Date endTime = orderPayment.getEndTime();
        Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        // 设置到订单结束时间
        c.setTime(endTime);

        long end = c.getTimeInMillis();
        cv_countdown.start(end - start);
    }
}
