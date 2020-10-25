package me.teenyda.fruit.model.home.new_fruit.adapter;

/**
 * author: teenyda
 * date: 2020/9/19
 * description:
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.utils.GlideApp;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
public class NewFruitAdapter extends RecyclerView.Adapter<NewFruitAdapter.ViewHolder> {

    private Context mContext;
    private List<DataBean> mDataBeans;
    public NewFruitAdapter(Context context, List<DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.frag_new_fruit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBean dataBean = mDataBeans.get(position);
        GlideApp.with(mContext)
                .load(mContext.getDrawable(dataBean.imageRes))
                .override(400, 800)
                .skipMemoryCache(true)                      //禁止Glide内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)  //不缓存资源
                .transition(withCrossFade())
                .into(holder.new_iv_fruit_img);
    }

    @Override
    public int getItemCount() {
        return mDataBeans != null ? mDataBeans.size(): 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView new_iv_fruit_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            new_iv_fruit_img = itemView.findViewById(R.id.new_iv_fruit_img);
        }
    }
}
