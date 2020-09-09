package me.teenyda.fruit_store.model.classify.base;

import android.app.Activity;
import android.content.Context;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.classify.base.presenter.ClassifyPresenter;
import me.teenyda.fruit_store.model.classify.base.view.IClassifyView;
import me.teenyda.fruit_store.model.main.MainActivity;

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
            mFragments.add(SimpleCardFragment.getInstance(title));
        }

        StatusBarUtil.setTranslucentForImageViewInFragment((Activity) getMContext(), 255,cv);

        mAdapter = new MyPagerAdapter(getFragmentManager());
        vp.setAdapter(mAdapter);

        tabLayout.setViewPager(vp);
    }

    @Override
    protected void doBuseness() {

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
