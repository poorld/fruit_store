package me.teenyda.fruit.model.myself.order.base;

import android.content.Context;
import android.content.Intent;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.order.base.adapter.OrderPagerAdapter;
import me.teenyda.fruit.model.myself.order.base.presenter.OrderPresenter;
import me.teenyda.fruit.model.myself.order.base.view.IOrderView;
import me.teenyda.fruit.model.myself.order.fragment.OrderListFragment;

/**
 * author: teenyda
 * date: 2020/9/21
 * description:
 */
public class OrderAct extends MvpActivity<IOrderView, OrderPresenter> implements IOrderView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrderAct.class);
        context.startActivity(intent);

    }

    @BindView(R.id.stl)
    SlidingTabLayout tabLayout;

    @BindView(R.id.vp)
    ViewPager vp;

    private OrderPagerAdapter mAdapter;


    private final String[] mTitles = {
            "全部", "待付款", "待发货"
            , "待收货", "待评价"
    };
    private ArrayList<Fragment> mFragments;

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_order;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this, mView);

        mFragments = new ArrayList<>();


        for (String title : mTitles) {
            mFragments.add(OrderListFragment.getInstance());
        }


        mAdapter = new OrderPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        tabLayout.setViewPager(vp);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
