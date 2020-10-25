package me.teenyda.fruit.model.classify.base;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.ProductCategory;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.model.classify.base.fragment.ProductListFrag;
import me.teenyda.fruit.model.classify.base.presenter.ClassifyPresenter;
import me.teenyda.fruit.model.classify.base.view.IClassifyView;
import me.teenyda.fruit.model.classify.info.ProductInfoActivity;

/**
 * author: teenyda
 * date: 2020/9/9
 * description:
 */
public class ClassifyFragment extends MvpRxFragment<IClassifyView, ClassifyPresenter> implements IClassifyView {

    @BindView(R.id.stl)
    SlidingTabLayout tabLayout;

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.cardview)
    CardView cv;

    @BindView(R.id.product_search)
    RelativeLayout product_search;

    private Unbinder mBind;

    private MyPagerAdapter mAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = { };

    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        mBind = ButterKnife.bind(this, mView);
    }

    @Override
    protected void requestData() {
        mPresenter.getProductCategory();
    }

    @OnClick({R.id.product_search})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.product_search:
                ProductInfoActivity.startActivity(getMContext());
                break;
        }
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setProductCategory(List<ProductCategory> categorys) {
        mTitles = new String[categorys.size()];
        for (int i = 0; i < categorys.size(); i++) {
            ProductCategory category = categorys.get(i);
            mTitles[i] = category.getName();
            mFragments.add(ProductListFrag.getInstance(category));
        }

        mAdapter = new MyPagerAdapter(getFragmentManager());
        vp.setAdapter(mAdapter);

        tabLayout.setViewPager(vp);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ProductListFrag frag = (ProductListFrag) mFragments.get(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
