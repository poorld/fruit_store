package me.teenyda.fruit.model.classify.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.Product;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.common.view.popupview.PopViewProductImg;
import me.teenyda.fruit.common.view.popupview.PopupSpecifications;
import me.teenyda.fruit.common.viewholder.MultipleTypesAdapter;
import me.teenyda.fruit.common.viewholder.NumIndicator;
import me.teenyda.fruit.common.viewholder.VideoHolder;
import me.teenyda.fruit.model.classify.info.adapter.ProductInfoAdapter;
import me.teenyda.fruit.model.classify.info.presenter.ProductInfoPresenter;
import me.teenyda.fruit.model.classify.info.view.IProductInfoView;

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

    @BindView(R.id.tv_product_buy)
    TextView tv_product_buy;

    @BindView(R.id.comments_no_data)
    RelativeLayout comments_no_data;

    @BindView(R.id.comments_data)
    RelativeLayout comments_data;

    private PopupSpecifications mSpecifications;
    private ProductInfoAdapter mInfoAdapter;

    public static void startActivity(Context context, Integer productId) {
        Intent intent = new Intent(context, ProductInfoActivity.class);
        // intent.putExtra("productId", productId);
        Bundle bundle = new Bundle();
        bundle.putInt("productId", productId);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    protected ProductInfoPresenter createPresenter() {
        return new ProductInfoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_product_info;
    }

    @Override
    protected void initView() {
        mBind = ButterKnife.bind(this);

        setTitleShow(true, "商品详情", false);

        mPrice1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        mPrice1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                // 设置闪一次2秒
                .setRepeatDelay(2000)
                // 没闪的地方亮一点点
                .setBaseAlpha(0.5f)
                .build();
        sfl.setShimmer(shimmer);
        sfl.startShimmer();


        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        product_info_rv.setLayoutManager(manager);
        // ProductInfoAdapter adapter = new ProductInfoAdapter(getMContext(), DataBean.getProductInfoData());
        mInfoAdapter = new ProductInfoAdapter(getMContext());
        product_info_rv.setAdapter(mInfoAdapter);
        product_info_rv.setNestedScrollingEnabled(false);

        GlideApp.with(getMContext())
                .load(R.drawable.comments1)
                .override(100, 100)
                .into(iv_comments1);

        mPop = new PopViewProductImg(getMContext());

        mSpecifications = new PopupSpecifications(getMContext());

    }


    @OnClick({R.id.iv_comments1, R.id.iv_comments2, R.id.tv_product_buy})
    public void onClick(View view) {


        // todo 待完善
        switch (view.getId()) {
            case R.id.iv_comments1:
                mPop.show(view, R.drawable.comments1);
                break;
            case R.id.iv_comments2:
                mPop.show(view, R.drawable.comments1);
                break;
            case R.id.tv_product_buy:
                mSpecifications.show(view);
                break;
        }
    }

    @Override
    protected void requestData() {
        Integer productId =  getIntent().getExtras().getInt("productId");
        // String productId = getIntent().getStringExtra("");
        // showToast(productId.toString());
        mPresenter.getProduct(productId);
        mPresenter.getComments(productId);
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

    @Override
    public void setProduct(Product product) {
        mBanner.setAdapter(new MultipleTypesAdapter(this, product.getProductBannerImages()))
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

        mInfoAdapter.addInfoImage(product.getProductInfoImages());
    }

    @Override
    public void setComments(List<Comments> comments) {
        if (comments.size() == 0) {
            comments_data.setVisibility(View.GONE);
            comments_no_data.setVisibility(View.VISIBLE);
        }
    }
}
