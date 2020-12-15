package me.teenyda.fruit.model.cart.settlement.adapter;

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
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.utils.GlideApp;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class CartOrderAdapter extends RecyclerView.Adapter<CartOrderAdapter.ViewHolder> {

    private Context mContext;
    private List<SettlementOrder> mOrders;
    private IContactAction mIContactAction;

    public interface IContactAction {
        void onContactSelect(Contact contact);
    }

    public CartOrderAdapter(Context context) {
        mContext = context;
        mOrders = new ArrayList<>();
    }

    public void setContactAction(IContactAction contactAction) {
        this.mIContactAction = contactAction;
    }

    public void addContacts(List<SettlementOrder> contacts) {
        if (contacts != null && contacts.size() > 0) {
            mOrders.clear();
            mOrders.addAll(contacts);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettlementOrder order = mOrders.get(position);
        SettlementOrder.OrderProduct product = order.getProduct();

        GlideApp.with(mContext)
                .load(product.getDefaultImg())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        holder.settle_product_img.setImageDrawable(drawable);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable drawable) {

                    }
                });
        Spec spec = product.getSpec();
        String price = spec.getPrice().toString();
        // 规格
        String attrbute = spec.getSku().getAttrbute();
        // 数量
        int quantity = order.getQuantity();
        holder.settle_product_spec.setText(String.format(mContext.getString(R.string.settlement_spec), spec.getSpecName(), attrbute));
        holder.settle_product_name.setText(product.getName());
        holder.settle_product_price.setText(String.format(mContext.getString(R.string.settlement_price), price));
        holder.settle_product_number.setText(String.format(mContext.getString(R.string.settlement_number), quantity));
        // holder.settle_total_number.setText(String.valueOf(quantity));
        // holder.tv_name.setText(contact.getName());
        // holder.address_mobile.setText(contact.getMobile());
        // holder.address.setText(contact.getAddress());
        // holder.itemView.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         if (mIContactAction != null) {
        //             mIContactAction.onContactSelect(contact);
        //         }
        //     }
        // });
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView settle_product_name;
        TextView settle_product_price;
        TextView settle_product_spec;
        TextView settle_product_number;
        ImageView settle_product_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            settle_product_name = itemView.findViewById(R.id.settle_product_name);
            settle_product_price = itemView.findViewById(R.id.settle_product_price);
            settle_product_spec = itemView.findViewById(R.id.settle_product_spec);
            settle_product_number = itemView.findViewById(R.id.settle_product_number);
            settle_product_img = itemView.findViewById(R.id.settle_product_img);
        }
    }
}
