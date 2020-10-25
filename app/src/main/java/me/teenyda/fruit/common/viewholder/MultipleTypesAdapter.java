package me.teenyda.fruit.common.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.entity.ProductBannerImage;
import me.teenyda.fruit.common.utils.GlideApp;

/**
 * 自定义布局,多个不同UI切换
 */
public class MultipleTypesAdapter extends BannerAdapter<ProductBannerImage, RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductBannerImage> mBannerImages;

    public MultipleTypesAdapter(Context context, List<ProductBannerImage> mDatas) {
        super(mDatas);
        this.context = context;
        mBannerImages = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
            case 2:
                return new VideoHolder(BannerUtils.getView(parent, R.layout.banner_video));
            case 3:
                return new TitleHolder(BannerUtils.getView(parent, R.layout.banner_title));
        }
        return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
    }

    @Override
    public int getItemViewType(int position) {
        int realPosition = getRealPosition(position);
        return mBannerImages.get(realPosition).getType();
        // return getData().viewType;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, ProductBannerImage data, int position, int size) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            // 图片
            case 1:
                ImageHolder imageHolder = (ImageHolder) holder;

                // imageHolder.imageView.setImageResource(data.imageRes);
                // 解决闪退问题imageView.setImageResource(data.imageRes);
                // 使用Glide
                GlideApp.with(context)
                        .load(data.getUrl())
                        .override(600, 400)
                        .centerCrop()
                        .into(imageHolder.imageView);

                break;
            case 2:
                // 视频
                VideoHolder videoHolder = (VideoHolder) holder;
                videoHolder.player.setUp(data.getUrl(), true, null);
                videoHolder.player.getBackButton().setVisibility(View.GONE);
                //增加封面
                ImageView imageView = new ImageView(context);
                GlideApp.with(context)
                        .load(R.drawable.image4)
                        .into(imageView);

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                videoHolder.player.setThumbImageView(imageView);
//                videoHolder.player.startPlayLogic();
                break;
            case 3:
                // 文字

                // todo 待完善 title
                TitleHolder titleHolder = (TitleHolder) holder;
                // titleHolder.title.setText(data.title);
                titleHolder.title.setBackgroundColor(Color.parseColor(DataBean.getRandColor()));
                break;
        }
    }



}
