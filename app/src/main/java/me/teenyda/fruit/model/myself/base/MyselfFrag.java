package me.teenyda.fruit.model.myself.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.common.utils.FileCacheUtil;
import me.teenyda.fruit.model.login.base.LoginAct;
import me.teenyda.fruit.model.myself.address.base.AddressAct;
import me.teenyda.fruit.model.myself.base.presenter.MyselfP;
import me.teenyda.fruit.model.myself.base.view.IMySelfV;
import me.teenyda.fruit.model.myself.information.InfoAct;
import me.teenyda.fruit.model.myself.member.MemberActivity;
import me.teenyda.fruit.model.myself.order.base.OrderAct;
import me.teenyda.fruit.model.myself.wallet.WalletActivity;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class MyselfFrag extends MvpRxFragment<IMySelfV, MyselfP> implements IMySelfV {

    @BindView(R.id.sfl)
    ShimmerFrameLayout sfl;

    @BindView(R.id.ll_member)
    LinearLayout ll_member;

    @BindView(R.id.myself_address)
    LinearLayout myself_address;

    @Override
    protected MyselfP createPresenter() {
        return new MyselfP();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_myself;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, mView);
        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                // 设置闪一次2秒
                .setRepeatDelay(2000)
                // 没闪的地方亮一点点
                .setBaseAlpha(0.5f)
                .build();
        sfl.setShimmer(shimmer);
        sfl.startShimmer();
    }

    @OnClick({R.id.ll_member,R.id.ll_wallet, R.id.wallet_order,R.id.myself_info,R.id.myself_logout,
                R.id.myself_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_member:
                MemberActivity.startActivity(getMContext());
                break;
            case R.id.ll_wallet:
                WalletActivity.startActivity(getMContext());
                break;
            case R.id.wallet_order:
                OrderAct.startActivity(getMContext());
                break;
            case R.id.myself_info:
                InfoAct.startActivity(getMContext());
                break;
            case R.id.myself_logout:
                logOut();
                break;
            case R.id.myself_address:
                AddressAct.startActivity(getMContext());
                break;
        }

    }

    private void logOut() {
        FileCacheUtil.removeUser(getMContext());
        LoginAct.startActivity(getMContext());
        ((Activity)getMContext()).finish();
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
