package me.teenyda.fruit.model.myself.order.fragment;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.constant.TabMenuEnum;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.model.myself.order.fragment.adapter.OrderListAdapter;
import me.teenyda.fruit.model.myself.order.fragment.presenter.OrderListPresenter;
import me.teenyda.fruit.model.myself.order.fragment.view.IOrderListView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class OrderListFragment extends MvpRxFragment<IOrderListView, OrderListPresenter> implements IOrderListView {

    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private Unbinder binder;
    private OrderListAdapter mAdapter;

    public static OrderListFragment getInstance() {
        OrderListFragment frag = new OrderListFragment();
        return frag;
    }

    @Override
    protected OrderListPresenter createPresenter() {
        return new OrderListPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.product_list_frag;
    }

    @Override
    protected void initView() {
        binder = ButterKnife.bind(this, mView);

        xrv.setLayoutManager(new LinearLayoutManager(getMContext()));
        mAdapter = new OrderListAdapter(getMContext());
        xrv.setAdapter(mAdapter);
    }

    @Override
    protected void requestData() {
        // mPresenter.getOrders(10001);
    }

    public void getOrders(int status) {
        if (status == TabMenuEnum.Menu1.getOrderStatus()) {
            mPresenter.getOrders(10001);
        }else {
            mPresenter.getOrders(10001, status);
        }
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setOrders(List<Order> orders) {
        mAdapter.addOrders(orders);
    }
}
