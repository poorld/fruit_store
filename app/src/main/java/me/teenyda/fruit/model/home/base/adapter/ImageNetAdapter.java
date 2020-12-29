package me.teenyda.fruit.model.home.base.adapter;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Banner;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.common.viewholder.ImageHolder;

/**
 * 自定义布局，网络图片
 */
public class ImageNetAdapter extends BannerAdapter<Banner, ImageHolder> {

    public BannerClickListener mBannerClickListener;

    public void setBannerClickListener(BannerClickListener clickListener) {
        mBannerClickListener = clickListener;
    }
    public interface BannerClickListener{
        void onBannerClick(Banner banner);
    }

    public ImageNetAdapter(List<Banner> datas) {
        super(datas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 20);
        }
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, Banner data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        GlideApp.with(holder.itemView)
                .load(data.getImage())
                // .skipMemoryCache(true)                      //禁止Glide内存缓存
                // .diskCacheStrategy(DiskCacheStrategy.NONE)  //不缓存资源
                .override(400, 170)
                .centerCrop()
                .thumbnail(Glide.with(holder.itemView).load(R.drawable.anmi_loading))
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
//                 .into(holder.imageView);
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        if (mBannerClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBannerClickListener.onBannerClick(data);
                }
            });
        }
    }



}
