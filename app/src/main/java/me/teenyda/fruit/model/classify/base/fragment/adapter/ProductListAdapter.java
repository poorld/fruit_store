package me.teenyda.fruit.model.classify.base.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import androidx.annotation.NonNull;
import me.teenyda.fruit.R;
import me.teenyda.fruit.model.classify.info.ProductInfoActivity;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductListAdapter extends XRecyclerView.Adapter<ProductListAdapter.ViewHolder>  {

    private Context mContext;

    public ProductListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mContext.getDrawable(R.drawable.product02))
                .override(400, 400)
                .centerCrop()
                .into(holder.product_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductInfoActivity.startActivity(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        ImageView product_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_img = itemView.findViewById(R.id.product_img);
        }
    }
}
