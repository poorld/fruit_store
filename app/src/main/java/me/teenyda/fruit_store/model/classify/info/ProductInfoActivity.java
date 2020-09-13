package me.teenyda.fruit_store.model.classify.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.DataBean;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.common.view.popupview.PopViewProductImg;
import me.teenyda.fruit_store.common.viewholder.MultipleTypesAdapter;
import me.teenyda.fruit_store.common.viewholder.NumIndicator;
import me.teenyda.fruit_store.common.viewholder.VideoHolder;
import me.teenyda.fruit_store.model.classify.info.adapter.ProductInfoAdapter;
import me.teenyda.fruit_store.model.classify.info.presenter.ProductInfoPresenter;
import me.teenyda.fruit_store.model.classify.info.view.IProductInfoView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductInfoActivity extends MvpActivity<IProductInfoView, ProductInfoPresenter> implements IProductInfoView {


    private Unbinder mBind;

    StandardGSYVideoPlayer player;

    @BindView(R.id.video_banner)
    Banner mBanner;

    @BindView(R.id.price1)
    TextView mPrice1;

    @BindView(R.id.sfl)
    ShimmerFrameLayout sfl;

    @BindView(R.id.product_info_rv)
    RecyclerView product_info_rv;

    @BindView(R.id.iv_comments1)
    ImageView iv_comments1;
    private PopViewProductImg mPop;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ProductInfoActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected ProductInfoPresenter createPresenter() {
        return new ProductInfoPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_product_info;
    }

    @Override
    protected void viewInitializer() {
        mBind = ButterKnife.bind(this);

        /*mBanner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
        mBanner.setBannerRound(BannerUtils.dp2px(5));
        mBanner.setIndicator(new RoundLinesIndicator(getMContext()));
        mBanner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));*/

        mPrice1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        mPrice1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);

        sfl.startShimmer();

        mBanner.setAdapter(new MultipleTypesAdapter(this, DataBean.getTestDataVideo()))
                .setIndicator(new NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .addOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.e("--","position:"+position);
                        if (player == null) {
                            RecyclerView.ViewHolder viewHolder = mBanner.getAdapter().getViewHolder();
                            if (viewHolder instanceof VideoHolder) {
                                VideoHolder holder = (VideoHolder) viewHolder;
                                player = holder.player;
                            }
                            return;
                        }
                        if (position != 0) {
                            player.onVideoReset();
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        product_info_rv.setLayoutManager(manager);
        ProductInfoAdapter adapter = new ProductInfoAdapter(getMContext(), DataBean.getProductInfoData());
        product_info_rv.setAdapter(adapter);
        product_info_rv.setNestedScrollingEnabled(false);

        Glide.with(getMContext())
                .load(R.drawable.comments1)
                .override(100, 100)
                .into(iv_comments1);

        mPop = new PopViewProductImg(getMContext());
    }


    @OnClick({R.id.iv_comments1, R.id.iv_comments2})
    public void onClick(View view) {

        mPop.show(view, R.drawable.comments1);

        // todo 待完善
        switch (view.getId()) {
            case R.id.iv_comments1:


                break;

        }
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null)
            player.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null)
            player.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        //释放所有
        if (player != null)
            player.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
