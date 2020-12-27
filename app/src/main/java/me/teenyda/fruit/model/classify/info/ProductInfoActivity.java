package me.teenyda.fruit.model.classify.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.constant.OrderTypeEnum;
import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.Product;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.FileCacheUtil;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.common.view.popupview.PopViewProductImg;
import me.teenyda.fruit.common.view.popupview.PopupSpecifications;
import me.teenyda.fruit.common.viewholder.MultipleTypesAdapter;
import me.teenyda.fruit.common.viewholder.NumIndicator;
import me.teenyda.fruit.common.viewholder.VideoHolder;
import me.teenyda.fruit.model.classify.comments.CommentsActivity;
import me.teenyda.fruit.model.classify.info.adapter.ProductInfoAdapter;
import me.teenyda.fruit.model.classify.info.presenter.ProductInfoPresenter;
import me.teenyda.fruit.model.classify.info.view.IProductInfoView;
import me.teenyda.fruit.model.classify.settlement.SettlementActicity;

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

    @BindView(R.id.product_name)
    TextView product_name;
    @BindView(R.id.product_explain)
    TextView product_explain;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.shop_price)
    TextView shop_price;

    @BindView(R.id.user_comments)
    TextView user_comments;

    @BindView(R.id.business_reply)
    TextView business_reply;

    @BindView(R.id.username1)
    TextView username1;


    @BindView(R.id.sfl)
    ShimmerFrameLayout sfl;

    @BindView(R.id.product_info_rv)
    RecyclerView product_info_rv;

    @BindView(R.id.iv_comments1)
    ImageView iv_comments1;

    @BindView(R.id.iv_comments2)
    ImageView iv_comments2;

    @BindView(R.id.iv_comments3)
    ImageView iv_comments3;

    @BindView(R.id.product_reply)
    LinearLayout product_reply;

    private PopViewProductImg mCommentsPop;

    // 购买
    @BindView(R.id.tv_product_buy)
    TextView tv_product_buy;

    // 购物车
    @BindView(R.id.tv_product_cart)
    TextView tv_product_cart;

    @BindView(R.id.comments_no_data)
    RelativeLayout comments_no_data;

    @BindView(R.id.comments_data)
    RelativeLayout comments_data;

    @BindView(R.id.to_comments)
    RelativeLayout to_comments;

    private PopupSpecifications mSpecifications;
    private ProductInfoAdapter mInfoAdapter;
    private List<String> mUrls;
    private Integer mProductId;

    // 购买
    private static final int order_type_buy = OrderTypeEnum.Order.getPaymentType();
    // 购物车
    private static final int order_type_cart = OrderTypeEnum.Cart.getPaymentType();
    private int mType;

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

        // 设置删除线
        shop_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        shop_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

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

        // GlideApp.with(getMContext())
        //         .load(R.drawable.comments1)
        //         .override(100, 100)
        //         .into(iv_comments1);

        mCommentsPop = new PopViewProductImg(getMContext());

        mSpecifications = new PopupSpecifications(getMContext());
        mSpecifications.setOrderConfirmClick(new PopupSpecifications.OrderConfirmClick() {
            @Override
            public void onOrderConfirmClick(Spec spec, int number) {
                // 下订单
                Integer userId = FileCacheUtil.getUser(getMContext()).getUserId();
                OrderItem orderItem = new OrderItem();
                orderItem.setUserId(userId);
                orderItem.setPrice(spec.getPrice());
                orderItem.setQuantity(number);
                orderItem.setSpecId(spec.getSpecId());
                orderItem.setProductId(mProductId);
                // 购买
                if (mType == order_type_buy) {
                    mPresenter.order(orderItem);
                } else {
                    // 购物车
                    mPresenter.addCart(orderItem);
                }
            }
        });
    }


    @OnClick({R.id.iv_comments1, R.id.iv_comments2, R.id.iv_comments3
            , R.id.tv_product_buy, R.id.tv_product_cart, R.id.to_comments})
    public void onClick(View view) {


        // todo 待完善
        switch (view.getId()) {
            case R.id.iv_comments1:
                mCommentsPop.show(view, 0);
                break;
            case R.id.iv_comments2:
                mCommentsPop.show(view, 1);
                break;
            case R.id.iv_comments3:
                mCommentsPop.show(view, 2);
                break;
            case R.id.tv_product_buy:
                mType = order_type_buy;
                mSpecifications.show(view);
                break;
            case R.id.tv_product_cart:
                mType = order_type_cart;
                mSpecifications.show(view);
                break;
            case R.id.to_comments:
                CommentsActivity.startAct(getMContext(), mProductId);
                break;
        }
    }

    @Override
    protected void requestData() {
        mProductId = getIntent().getExtras().getInt("productId");
        // String productId = getIntent().getStringExtra("");
        // showToast(productId.toString());
        mPresenter.getProduct(mProductId);
        mPresenter.getComments(mProductId);
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

        shop_price.setText(product.getShopPrice().toString());
        price.setText(product.getPrice().toString());
        product_name.setText(product.getName());
        product_explain.setText(product.getExplain());

        mSpecifications.setSpecList(product.getSpec());
        mSpecifications.setSpecImg(product.getDefaultImg());
    }

    @Override
    public void setComments(Comments comments) {
        if (comments != null) {
            mUrls = new ArrayList<>();

            setImageView(comments.getImg1(), iv_comments1);
            setImageView(comments.getImg2(), iv_comments2);
            setImageView(comments.getImg3(), iv_comments3);

            mCommentsPop.addImgsByUrl(mUrls);
            username1.setText(comments.getUser().getNickname());
            user_comments.setText(comments.getContent());

            String reply = comments.getReply();
            if (TextUtils.isEmpty(reply)) {
                product_reply.setVisibility(View.GONE);
            } else {
                product_reply.setVisibility(View.VISIBLE);
                business_reply.setText("商家回复" + reply);
            }

        } else {
            comments_data.setVisibility(View.GONE);
            comments_no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void orderSuccess(OrderItem orderItem) {
        SettlementActicity.startActivity(getMContext(), orderItem.getOrderNum());
    }

    @Override
    public void addCartSuccess(OrderItem orderItem) {
        showToast("添加成功");
        mSpecifications.dismiss();
    }

    public void setImageView(String url, ImageView iv) {
        if (!TextUtils.isEmpty(url)) {
            mUrls.add(url);

            iv.setVisibility(View.VISIBLE);

            GlideApp.with(this)
                    .load(url)
                    .override(100, 100)
                    .centerCrop()
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            iv.setImageDrawable(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }
}
