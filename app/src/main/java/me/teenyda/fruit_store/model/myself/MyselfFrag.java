package me.teenyda.fruit_store.model.myself;

import android.content.Context;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpFragment;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.myself.base.presenter.MyselfP;
import me.teenyda.fruit_store.model.myself.base.view.IMySelfV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class MyselfFrag extends MvpRxFragment<IMySelfV, MyselfP> implements IMySelfV {

    @BindView(R.id.sfl)
    ShimmerFrameLayout sfl;

    @Override
    protected MyselfP createPresenter() {
        return new MyselfP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_myself;
    }

    @Override
    protected void viewInitializer() {
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

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
