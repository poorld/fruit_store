package me.teenyda.fruit.model.classify.base.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.model.classify.info.ProductInfoActivity;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductListAdapter extends XRecyclerView.Adapter<ProductListAdapter.ViewHolder>  {

    private Context mContext;

    private List<SimpleProductEntity> mProducts;

    public ProductListAdapter(Context context) {
        mContext = context;
        mProducts = new ArrayList<>();
    }

    public void addProducts(List<SimpleProductEntity> products) {
        this.mProducts.clear();
        this.mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleProductEntity product = mProducts.get(position);
        GlideApp.with(mContext)
                .load(product.getDefaultImg())
                .skipMemoryCache(true)                      //禁止Glide内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)  //不缓存资源
                .transition(withCrossFade())
                .override(400, 400)
                .centerCrop()
                .into(holder.product_img);

        holder.item_product_recommend.setVisibility(product.getRecommended() ? View.VISIBLE : View.INVISIBLE);
        holder.item_product_name.setText(product.getName());
        holder.item_product_price.setText(product.getPrice().toString());// String.valueOf

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductInfoActivity.startActivity(mContext, product.getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        ImageView product_img;
        ImageView item_product_recommend;
        TextView item_product_name;
        TextView item_product_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_img = itemView.findViewById(R.id.product_img);
            item_product_recommend = itemView.findViewById(R.id.item_product_recommend);
            item_product_name = itemView.findViewById(R.id.item_product_name);
            item_product_price = itemView.findViewById(R.id.item_product_price);
        }
    }
}
