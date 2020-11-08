package me.teenyda.fruit.model.classify.payment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.constant.PaymentStatusEnum;
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

    @BindView(R.id.payment_order_number)
    TextView payment_order_number;

    @BindView(R.id.payment_state)
    TextView payment_state;

    @BindView(R.id.payment_money)
    TextView payment_money;

    @BindView(R.id.payment_pay)
    TextView payment_pay;

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

    @OnClick({R.id.payment_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment_pay:
                String orderNumber = getIntent().getStringExtra("orderNumber");
                mPresenter.pay(orderNumber);
                break;
        }
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

        cv_countdown.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                payment_state.setText("订单已结束");
                payment_pay.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void requestData() {
        String orderNumber = getIntent().getStringExtra("orderNumber");
        mPresenter.getOrderPayment(orderNumber);
        payment_order_number.setText(String.format(getString(R.string.payment_order_num), orderNumber));
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setOrderPayment(OrderPayment orderPayment) {
        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.values()[orderPayment.getPayStatus()];
        payment_state.setText(paymentStatusEnum.getDesc());
        payment_money.setText(orderPayment.getPayAmount().toString());

        Date endTime = orderPayment.getEndTime();
        Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        // 设置到订单结束时间
        c.setTime(endTime);

        long end = c.getTimeInMillis();
        // 如果当前时间大于 订单结束时间 ，结束订单
        if (new Date().after(endTime)) {
            // api
            mPresenter.finish(orderPayment.getOrderNum());
            payment_state.setText("订单已结束");
            payment_pay.setVisibility(View.GONE);
        }else {
            cv_countdown.start(end - start);

        }
    }

    @Override
    public void paySuccess(OrderPayment orderPayment) {

        if (orderPayment.getPayStatus() ==
                PaymentStatusEnum.PAYMENT.getPaymentStatus()) {
            showToast("支付成功!");
            payment_state.setText("订单已支付");
            payment_pay.setVisibility(View.GONE);
            cv_countdown.stop();
        } else {
            showToast("支付失败!");
        }
    }
}
