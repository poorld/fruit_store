package me.teenyda.fruit.model.home.base.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.utils.GlideApp;

/**
 * author: teenyda
 * date: 2020/9/9
 * description:
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private Context mContext;



    public RecommendAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_market_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        holder.tv_market_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
        GlideApp.with(mContext)
                .load(mContext.getDrawable(R.drawable.show_apple))
                .override(50, 50)
                .centerCrop()
                .into(holder.recomm_iv);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_market_price;
        ImageView recomm_iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_market_price = itemView.findViewById(R.id.tv_market_price);
            recomm_iv = itemView.findViewById(R.id.recomm_iv);
        }
    }
}
