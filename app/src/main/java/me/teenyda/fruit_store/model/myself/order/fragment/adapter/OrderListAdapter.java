package me.teenyda.fruit_store.model.myself.order.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import androidx.annotation.NonNull;
import me.teenyda.fruit_store.R;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class OrderListAdapter extends XRecyclerView.Adapter<OrderListAdapter.ViewHolder>  {

    private Context mContext;

    public OrderListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends XRecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
