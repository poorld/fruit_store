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
import me.teenyda.fruit.common.constant.TabMenuEnum;
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


    private String[] mTitles;
    private final TabMenuEnum[] mTabMenus = {
        TabMenuEnum.Menu1,
        TabMenuEnum.Menu2,
        TabMenuEnum.Menu3,
        TabMenuEnum.Menu4,
        TabMenuEnum.Menu5
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
        mTitles = new String[mTabMenus.length];


        for (int i = 0; i < mTabMenus.length; i++) {
            mTitles[i] = mTabMenus[i].getDesc();
            mFragments.add(OrderListFragment.getInstance());
        }

        mAdapter = new OrderPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        tabLayout.setViewPager(vp);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                OrderListFragment fragment = (OrderListFragment) mFragments.get(position);
                fragment.getOrders(mTabMenus[position].getOrderStatus());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
