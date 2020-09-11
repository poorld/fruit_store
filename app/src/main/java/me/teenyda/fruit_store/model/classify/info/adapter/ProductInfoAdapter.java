package me.teenyda.fruit_store.model.classify.info.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.DataBean;
import me.teenyda.fruit_store.common.viewholder.TransformationUtils;

/**
 * author: teenyda
 * date: 2020/9/11
 * description:
 */
public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ViewHolder> {

    private Context mContext;

    private List<DataBean> mDataBeans;

    public ProductInfoAdapter(Context context,List<DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_info_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBean dataBean = mDataBeans.get(position);
        String imageUrl = dataBean.imageUrl;
        Glide.with(mContext).load(imageUrl)
                .override(780, 960)
                .into(holder.image);

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
