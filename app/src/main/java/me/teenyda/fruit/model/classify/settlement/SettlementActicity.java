package me.teenyda.fruit.model.classify.settlement;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Discounts;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.common.utils.ToolUtils;
import me.teenyda.fruit.common.view.popupview.PopupPayment;
import me.teenyda.fruit.model.classify.settlement.presenter.SettlementPresenter;
import me.teenyda.fruit.model.classify.settlement.view.ISettlementView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description: 结算
 */
public class SettlementActicity extends MvpActivity<ISettlementView, SettlementPresenter> implements ISettlementView {

    @BindView(R.id.ll_payment)
    LinearLayout ll_payment;

    @BindView(R.id.settle_product_img)
    ImageView settle_product_img;

    @BindView(R.id.settle_product_name)
    TextView settle_product_name;

    @BindView(R.id.settle_product_price)
    TextView settle_product_price;

    @BindView(R.id.settle_product_spec)
    TextView settle_product_spec;

    @BindView(R.id.settle_product_number)
    TextView settle_product_number;

    @BindView(R.id.settle_product_discount)
    TextView settle_product_discount;

    @BindView(R.id.settle_total_number)
    TextView settle_total_number;

    @BindView(R.id.settle_total_price)
    TextView settle_total_price;

    private PopupPayment mPopupPayment;

    private User mUser;
    private List<Discounts> mDiscounts;

    public static void startActivity(Context context, String orderNumber) {
        Intent intent = new Intent(context, SettlementActicity.class);
        intent.putExtra("order_number", orderNumber);
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
        String orderNumber = getIntent().getStringExtra("order_number");
        mPresenter.getOrder(orderNumber);
        mPresenter.getUserInfo(10001);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setUserInfo(User userInfo) {
        this.mUser = userInfo;
        if (userInfo.getMembers() != null) {
            mPresenter.getDiscount(true);
        } else {
            mPresenter.getDiscount(false);
        }
    }

    @Override
    public void setDiscount(List<Discounts> discounts) {
        this.mDiscounts = discounts;
    }

    @Override
    public void setOrderItem(SettlementOrder orderItem) {
        SettlementOrder.OrderProduct product = orderItem.getProduct();
        GlideApp.with(this)
                .load(product.getDefaultImg())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        settle_product_img.setImageDrawable(drawable);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable drawable) {

                    }
                });
        Spec spec = product.getSpec();
        String price = spec.getPrice().toString();
        // 规格
        String attrbute = spec.getSku().getAttrbute();
        // 数量
        Integer quantity = orderItem.getQuantity();
        settle_product_name.setText(product.getName());
        settle_product_price.setText(String.format(getString(R.string.settlement_price), price));
        settle_product_number.setText(String.format(getString(R.string.settlement_number), quantity));
        settle_total_number.setText(quantity.toString());

        // 计算优惠
        double totalPrice = ToolUtils.mul(quantity, spec.getPrice());
        Discounts discounts = preferential(totalPrice);
        if (discounts != null) {
            settle_product_discount.setText(discounts.getDiscountsExplain());
            totalPrice = ToolUtils.sub(totalPrice, discounts.getConditions());
        } else {
            settle_product_discount.setText("暂无优惠");
        }

        settle_total_price.setText(String.valueOf(totalPrice));
    }

    private Discounts preferential(double totalPrice) {
        for (Discounts discounts: mDiscounts) {
            // 满足条件的金额
            Integer conditions = discounts.getConditions();
            if (totalPrice >= conditions) {
                return discounts;
            }
        }
        return null;
    }
}
