package me.teenyda.fruit_store.model.main;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.model.classify.base.ClassifyFragment;
import me.teenyda.fruit_store.model.home.base.HomeFxFrag;
import me.teenyda.fruit_store.model.myself.MyselfFrag;
import me.teenyda.fruit_store.model.store.base.StoreFrag;

public class MainActivity extends RxAppCompatActivity {

    private HomeFxFrag mHomeFrag;
    private StoreFrag mStoreFrag;
    private MyselfFrag mMyselfFrag;
    private ClassifyFragment mClassifyFrag;
    private RxFragment mCurrentFragment;

    @BindView(R.id.home_rl)
    RelativeLayout home_rl;

    @BindView(R.id.store_rl)
    RelativeLayout store_rl;

    @BindView(R.id.myself_rl)
    RelativeLayout myself_rl;

    @BindView(R.id.home_iv)
    ImageView home_iv;
    @BindView(R.id.store_iv)
    ImageView store_iv;
    @BindView(R.id.myself_iv)
    ImageView myself_iv;

    @BindView(R.id.home_tv)
    TextView home_tv;
    @BindView(R.id.store_tv)
    TextView store_tv;
    @BindView(R.id.myself_tv)
    TextView myself_tv;


    private FragmentManager manager;
    private Unbinder mBind;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        home_rl.performClick();

//        StatusBarUtil.setColor(this, getColor(R.color.c_00000000));
    }

    @OnClick({R.id.home_rl, R.id.store_rl, R.id.myself_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_rl:
                switchNavigation(0);
                switchIcon(0);
                break;
            case R.id.store_rl:
                switchNavigation(1);
                switchIcon(1);
                break;
            case R.id.myself_rl:
                switchNavigation(2);
                switchIcon(2);
                break;
        }
    }
    private void switchNavigation(int index) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (index) {
            case 0:

                if (mHomeFrag == null)
                    mHomeFrag = new HomeFxFrag();

                if (!mHomeFrag.isAdded())
                    transaction.add(R.id.main_frame, mHomeFrag);

                if (mCurrentFragment != null && mCurrentFragment != mHomeFrag) {
                    transaction.show(mHomeFrag).hide(mCurrentFragment).commit();
                } else {
                    transaction.show(mHomeFrag).commit();
                }
                StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);



                mCurrentFragment = mHomeFrag;
                break;
            case 1:

                if (mClassifyFrag == null)
                    mClassifyFrag = new ClassifyFragment();

                if (!mClassifyFrag.isAdded())
                    transaction.add(R.id.main_frame, mClassifyFrag);

                if (mCurrentFragment != null && mCurrentFragment != mClassifyFrag) {
                    transaction.show(mClassifyFrag).hide(mCurrentFragment).commit();
                }
                // StatusBarUtil.setColor(MainActivity.this, getColor(R.color.colorPrimary));
                StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
                mCurrentFragment = mClassifyFrag;
                break;
//            case 2:
//                if (mMyselfFrag == null) {
//                    mMyselfFrag = new MyselfFrag();
//                }
//
//                if (!mMyselfFrag.isAdded())
//                    transaction.add(R.id.main_frame, mMyselfFrag);
//
//                if (mCurrentFragment != null && mCurrentFragment != mMyselfFrag) {
//                    transaction.show(mMyselfFrag).hide(mCurrentFragment).commit();
//                }
//
//                mCurrentFragment = mMyselfFrag;
//                break;
        }
    }

    private void switchIcon(int index) {
        switch (index) {
            case 0:
                home_iv.setSelected(true);
                store_iv.setSelected(false);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getColor(R.color.c_ffc440));
                store_tv.setTextColor(getColor(R.color.c_b2b2b2));
                myself_tv.setTextColor(getColor(R.color.c_b2b2b2));
                break;
            case 1:
                home_iv.setSelected(false);
                store_iv.setSelected(true);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getColor(R.color.c_b2b2b2));
                store_tv.setTextColor(getColor(R.color.c_ffc440));
                myself_tv.setTextColor(getColor(R.color.c_b2b2b2));
                break;
            case 2:
                home_iv.setSelected(false);
                store_iv.setSelected(false);
                myself_iv.setSelected(true);

                home_tv.setTextColor(getColor(R.color.c_b2b2b2));
                store_tv.setTextColor(getColor(R.color.c_b2b2b2));
                myself_tv.setTextColor(getColor(R.color.c_ffc440));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mCurrentFragment != null) {
            mCurrentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
