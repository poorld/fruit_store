package me.teenyda.fruit.model.classify.info.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.entity.ProductInfoImage;
import me.teenyda.fruit.common.utils.GlideApp;

/**
 * author: teenyda
 * date: 2020/9/11
 * description:
 */
public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ViewHolder> {

    private Context mContext;

    private List<ProductInfoImage> mDataBeans;

    public ProductInfoAdapter(Context context) {
        mContext = context;
        mDataBeans = new ArrayList<>();
    }

    public void addInfoImage(List<ProductInfoImage> dataBeans) {
        mDataBeans.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_info_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductInfoImage infoImage = mDataBeans.get(position);
        String imageUrl = infoImage.getUrl();
        GlideApp.with(mContext)
                .load(imageUrl)
                .override(780, 960)
                .centerCrop()
                // .into(holder.image);
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.image.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pro_info_img);
        }
    }
}
