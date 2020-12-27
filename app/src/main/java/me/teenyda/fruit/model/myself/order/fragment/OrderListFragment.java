package me.teenyda.fruit.model.myself.order.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.constant.TabMenuEnum;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.common.utils.FileCacheUtil;
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
    private int mStatus;

    public static OrderListFragment getInstance(int status) {
        OrderListFragment frag = new OrderListFragment();
        frag.mStatus = status;
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
        // 确认收货
        mAdapter.setTakeDeliveryListener(new OrderListAdapter.ITakeDeliveryListener() {
            @Override
            public void onTakeDelivery(Order order) {
                mPresenter.orderComplete(order.getOrderNum());
            }
        });
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Integer userId = FileCacheUtil.getUser(getMContext()).getUserId();
                if (mStatus == TabMenuEnum.Menu1.getOrderStatus()) {
                    mPresenter.getOrders(userId);
                }else {
                    mPresenter.getOrders(userId, mStatus);
                }
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    protected void requestData() {
        // mPresenter.getOrders(10001);
    }

    public void getOrders() {
        xrv.refresh();
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setOrders(List<Order> orders) {
        mAdapter.addOrders(orders);
        xrv.refreshComplete();
    }

    @Override
    public void takeDeliverySuccess() {
        xrv.refresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            xrv.refresh();
        }
    }
}
