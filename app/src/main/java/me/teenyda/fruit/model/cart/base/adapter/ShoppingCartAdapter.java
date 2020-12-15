package me.teenyda.fruit.model.cart.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Cart;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderItemDto;
import me.teenyda.fruit.common.entity.OrderProductDto;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.common.utils.ToolUtils;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class ShoppingCartAdapter extends XRecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private Context mContext;

    private List<Order> mCarts;

    private IProductSelect mProductSelect;

    private boolean selectAll;

    private List<Order> mSelectProduct;

    public void setCarts(List<Order> carts) {
        this.mCarts = carts;
        mSelectProduct = new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface IProductSelect{
        void onProductedSelect(double price, boolean isSelectedAll, List<Order> selectProduct);
    }

    public void selectedAll(boolean selected) {
        if (mCarts != null) {
            if (selected) {
                mSelectProduct = mCarts;
            } else {
                mSelectProduct.clear();
            }
            for (Order cart : mCarts) {
                if (selected) {
                    cart.getOrderItems().get(0).setSelected(true);
                } else {
                    cart.getOrderItems().get(0).setSelected(false);
                }
            }
            notifyDataSetChanged();
            mProductSelect.onProductedSelect(compuatePrice(), selected, mSelectProduct);
        }
    }

    public void setProductSelect(IProductSelect productSelect) {
        mProductSelect = productSelect;
    }

    public ShoppingCartAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ietm_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mCarts != null) {
            Order order = mCarts.get(position);
            OrderItemDto cart = order.getOrderItems().get(0);
            holder.iv_cart_selected.setSelected(cart.isSelected());

            // holder.iv_cart_fruit_img
            OrderProductDto product = cart.getProduct();
            Spec spec = product.getSpec();
            String specName = spec.getSpecName();
            String attrbute = spec.getSku().getAttrbute();
            holder.tv_cart_fruit_name.setText(product.getName());
            holder.tv_cart_price.setText(String.format(mContext.getString(R.string.cart_price), String.valueOf(cart.getPrice())));
            holder.tv_cart_spec.setText(String.format(mContext.getString(R.string.cart_spec), specName + "/" + attrbute));
            holder.tv_cart_count.setText(String.format(mContext.getString(R.string.cart_count), cart.getQuantity()));

            holder.iv_cart_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cart.setSelected(!cart.isSelected());
                    mProductSelect.onProductedSelect(compuatePrice(), selectAll, mSelectProduct);
                    notifyDataSetChanged();
                }
            });

            GlideApp.with(mContext)
                    .load(product.getDefaultImg())
                    .override(100, 100)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            holder.iv_cart_fruit_img.setImageDrawable(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }

    private double compuatePrice() {
        if (mCarts != null) {

            double totalPrice = 0;
            selectAll = true;
            mSelectProduct.clear();

            for (Order cart : mCarts) {
                OrderItemDto orderItemDto = cart.getOrderItems().get(0);
                if (orderItemDto.isSelected()) {
                    mSelectProduct.add(cart);
                    Double price = orderItemDto.getPrice();
                    int count = orderItemDto.getQuantity();
                    double mul = ToolUtils.mul(price, count);
                    totalPrice = ToolUtils.add(totalPrice, mul);
                } else {
                    selectAll = false;
                }
            }

            return ToolUtils.scaleDouble(totalPrice, 2);
        }
        return 0.0;
    }


    @Override
    public int getItemCount() {
        return mCarts != null ? mCarts.size() : 0;
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        ImageView iv_cart_selected;
        ImageView iv_cart_fruit_img;

        TextView tv_cart_fruit_name;
        TextView tv_cart_price;
        TextView tv_cart_spec;
        TextView tv_cart_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cart_selected = itemView.findViewById(R.id.iv_cart_selected);
            iv_cart_fruit_img = itemView.findViewById(R.id.iv_cart_fruit_img);
            tv_cart_fruit_name = itemView.findViewById(R.id.tv_cart_fruit_name);
            tv_cart_price = itemView.findViewById(R.id.tv_cart_price);
            tv_cart_spec = itemView.findViewById(R.id.tv_cart_spec);
            tv_cart_count = itemView.findViewById(R.id.tv_cart_count);
        }
    }
}
