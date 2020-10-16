package me.teenyda.fruit.model.myself.order.fragment;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
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

    public static OrderListFragment getInstance() {
        OrderListFragment frag = new OrderListFragment();
        return frag;
    }

    @Override
    protected OrderListPresenter createPresenter() {
        return new OrderListPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.product_list_frag;
    }

    @Override
    protected void viewInitializer() {
        binder = ButterKnife.bind(this, mView);

        xrv.setLayoutManager(new LinearLayoutManager(getMContext()));
        xrv.setAdapter(new OrderListAdapter(getMContext()));
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
