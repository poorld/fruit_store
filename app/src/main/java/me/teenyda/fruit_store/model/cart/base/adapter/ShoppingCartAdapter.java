package me.teenyda.fruit_store.model.cart.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.annotation.NonNull;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.Cart;
import me.teenyda.fruit_store.common.utils.ToolUtils;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class ShoppingCartAdapter extends XRecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private Context mContext;

    private List<Cart> mCarts;

    private IProductSelect mProductSelect;

    private boolean selectAll;

    public void setCarts(List<Cart> carts) {
        this.mCarts = carts;
        notifyDataSetChanged();
    }

    public interface IProductSelect{
        void onProductedSelect(double price, boolean isSelectedAll);
    }

    public void selectedAll(boolean selected) {
        if (mCarts != null) {
            for (Cart cart : mCarts) {
                if (selected) {
                    cart.setSelected(true);
                } else {
                    cart.setSelected(false);
                }
            }
            notifyDataSetChanged();
            mProductSelect.onProductedSelect(compuatePrice(), selected);
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
            Cart cart = mCarts.get(position);
            holder.iv_cart_selected.setSelected(cart.isSelected());

            // holder.iv_cart_fruit_img

            holder.tv_cart_fruit_name.setText(cart.getName());
            holder.tv_cart_price.setText(String.format(mContext.getString(R.string.cart_price), cart.getPrice()));
            holder.tv_cart_spec.setText(String.format(mContext.getString(R.string.cart_spec), cart.getSpecification()));
            holder.tv_cart_count.setText(String.format(mContext.getString(R.string.cart_count), cart.getCount()));

            holder.iv_cart_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cart.setSelected(!cart.isSelected());
                    mProductSelect.onProductedSelect(compuatePrice(), selectAll);
                    notifyDataSetChanged();
                }
            });
        }
    }

    private double compuatePrice() {
        if (mCarts != null) {
            double totalPrice = 0;
            selectAll = true;

            for (Cart cart : mCarts) {
                if (cart.isSelected()) {
                    float price = cart.getPrice();
                    int count = cart.getCount();
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
