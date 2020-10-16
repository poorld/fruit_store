package me.teenyda.fruit.model.home.base;

import android.content.Context;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.mvp.MvpFragment;
import me.teenyda.fruit.model.home.base.adapter.ImageNetAdapter;
import me.teenyda.fruit.model.home.base.presenter.HomeP;
import me.teenyda.fruit.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFrag extends MvpFragment<IHomeV, HomeP> implements IHomeV {

    @BindView(R.id.banner)
    Banner banner;

    private Unbinder mBind;


    @Override
    protected HomeP createPresenter() {
        return new HomeP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_home;
    }

    @Override
    protected void viewInitializer() {
        mBind = ButterKnife.bind(this, mView);

        //--------------------------简单使用-------------------------------
        // banner.addBannerLifecycleObserver(this)//添加生命周期观察者
        //         .setAdapter(new BannerExampleAdapter(DataBean.getTestData()))
        //         .setIndicator(new CircleIndicator(getMContext()));

        //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
        banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
        banner.setBannerRound(BannerUtils.dp2px(5));
        banner.setIndicator(new RoundLinesIndicator(getMContext()));
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));

    }



    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }



}
