package me.teenyda.fruit.model.myself.order.fragment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.OrderItemDto;
import me.teenyda.fruit.common.entity.OrderProductDto;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.utils.GlideApp;

/**
 * author: teenyda
 * date: 2020/11/9
 * description:
 */
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private Context mContext;

    private List<OrderItemDto> mOrderItems;

    public OrderItemAdapter(Context context) {
        this.mContext = context;
        mOrderItems = new ArrayList<>();
    }

    public void addOrderItems(List<OrderItemDto> orderItems){
        mOrderItems.addAll(orderItems);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_item, parent, false);
        return new OrderItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItemDto orderItemDto = mOrderItems.get(position);

        Double price = orderItemDto.getPrice();
        Integer quantity = orderItemDto.getQuantity();
        OrderProductDto product = orderItemDto.getProduct();
        String defaultImg = product.getDefaultImg();
        String name = product.getName();
        Spec spec = product.getSpec();
        String specName = spec.getSpecName();
        String attrbute = spec.getSku().getAttrbute();

        holder.order_fruit_name.setText(name);
        holder.order_spec.setText(specName + "/" + attrbute);
        holder.order_count.setText("x" + quantity);

        GlideApp.with(mContext)
                .load(defaultImg)
                .override(50, 50)
                .centerCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.order_img.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return mOrderItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView order_img;
        TextView order_fruit_name;
        TextView order_spec;
        TextView order_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_img = itemView.findViewById(R.id.order_img);
            order_fruit_name = itemView.findViewById(R.id.order_fruit_name);
            order_spec = itemView.findViewById(R.id.order_spec);
            order_count = itemView.findViewById(R.id.order_count);
        }
    }
}
