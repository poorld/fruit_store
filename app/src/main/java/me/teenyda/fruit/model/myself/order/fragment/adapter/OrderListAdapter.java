package me.teenyda.fruit.model.myself.order.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.constant.OrderStatusEnum;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderItemDto;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class OrderListAdapter extends XRecyclerView.Adapter<OrderListAdapter.ViewHolder>  {

    private Context mContext;

    private List<Order> mOrders;

    public OrderListAdapter(Context context) {
        mContext = context;
        mOrders = new ArrayList<>();
    }

    public void addOrders(List<Order> orders) {
        mOrders.addAll(orders);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrders.get(position);

        holder.order_number.setText(String.format(mContext.getString(R.string.payment_order_num), order.getOrderNum()));

        OrderStatusEnum orderStatusEnum = OrderStatusEnum.values()[order.getStatus()];
        holder.order_status.setText(orderStatusEnum.getDesc());
        int paymentType = orderStatusEnum.getPaymentType();
        switch (paymentType) {
            case 1:
                holder.order_logistics.setVisibility(View.GONE);
                holder.order_pay.setVisibility(View.VISIBLE);
                holder.order_cancel.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.order_pay.setVisibility(View.GONE);
                holder.order_cancel.setVisibility(View.GONE);
                holder.order_logistics.setVisibility(View.GONE);
                break;
            case 3:
                holder.order_pay.setVisibility(View.GONE);
                holder.order_cancel.setVisibility(View.GONE);
                holder.order_logistics.setVisibility(View.VISIBLE);
                break;
            case 4:
                holder.order_pay.setVisibility(View.GONE);
                holder.order_cancel.setVisibility(View.GONE);
                holder.order_logistics.setVisibility(View.VISIBLE);
                holder.order_logistics.setText("立即评价");
                break;
        }

        List<OrderItemDto> orderItems = order.getOrderItems();
        holder.item_order_rv.setLayoutManager(new LinearLayoutManager(mContext));
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(mContext);
        orderItemAdapter.addOrderItems(orderItems);
        holder.item_order_rv.setAdapter(orderItemAdapter);

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        RecyclerView item_order_rv;
        TextView order_number;
        TextView order_status;
        TextView order_logistics;
        TextView order_cancel;
        TextView order_pay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_order_rv = itemView.findViewById(R.id.item_order_rv);
            order_number = itemView.findViewById(R.id.order_number);
            order_status = itemView.findViewById(R.id.order_status);
            order_logistics = itemView.findViewById(R.id.order_logistics);
            order_cancel = itemView.findViewById(R.id.order_cancel);
            order_pay = itemView.findViewById(R.id.order_pay);
        }
    }
}
