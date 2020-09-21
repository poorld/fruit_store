package me.teenyda.fruit_store.model.classify.base;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.classify.base.fragment.ProductListFrag;
import me.teenyda.fruit_store.model.classify.base.presenter.ClassifyPresenter;
import me.teenyda.fruit_store.model.classify.base.view.IClassifyView;
import me.teenyda.fruit_store.model.classify.info.ProductInfoActivity;

/**
 * author: teenyda
 * date: 2020/9/9
 * description:
 */
public class ClassifyFragment extends MvpRxFragment<IClassifyView, ClassifyPresenter> {

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

    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };

    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void viewInitializer() {
        mBind = ButterKnife.bind(this, mView);

        for (String title : mTitles) {
            mFragments.add(ProductListFrag.getInstance());
        }


        mAdapter = new MyPagerAdapter(getFragmentManager());
        vp.setAdapter(mAdapter);

        tabLayout.setViewPager(vp);
    }

    @Override
    protected void doBuseness() {

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
