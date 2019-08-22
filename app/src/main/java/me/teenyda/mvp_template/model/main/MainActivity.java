package me.teenyda.mvp_template.model.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.model.home.base.HomeFrag;
import me.teenyda.mvp_template.model.myself.MyselfFrag;
import me.teenyda.mvp_template.model.store.base.StoreFrag;

public class MainActivity extends AppCompatActivity {

    private HomeFrag mHomeFrag;
    private StoreFrag mStoreFrag;
    private MyselfFrag mMyselfFrag;
    private Fragment mCurrentFragment;

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
                    mHomeFrag = new HomeFrag();

                if (!mHomeFrag.isAdded())
                    transaction.add(R.id.main_frame, mHomeFrag);

                if (mCurrentFragment != null && mCurrentFragment != mHomeFrag) {
                    transaction.show(mHomeFrag).hide(mCurrentFragment).commit();
                } else {
                    transaction.show(mHomeFrag).commit();
                }

                mCurrentFragment = mHomeFrag;
                break;
            case 1:

                if (mStoreFrag == null)
                    mStoreFrag = new StoreFrag();

                if (!mStoreFrag.isAdded())
                    transaction.add(R.id.main_frame, mStoreFrag);

                if (mCurrentFragment != null && mCurrentFragment != mStoreFrag) {
                    transaction.show(mStoreFrag).hide(mCurrentFragment).commit();
                }

                mCurrentFragment = mStoreFrag;
                break;
            case 2:
                if (mMyselfFrag == null) {
                    mMyselfFrag = new MyselfFrag();
                }

                if (!mMyselfFrag.isAdded())
                    transaction.add(R.id.main_frame, mMyselfFrag);

                if (mCurrentFragment != null && mCurrentFragment != mMyselfFrag) {
                    transaction.show(mMyselfFrag).hide(mCurrentFragment).commit();
                }

                mCurrentFragment = mMyselfFrag;
                break;
        }
    }

    private void switchIcon(int index) {
        switch (index) {
            case 0:
                home_iv.setSelected(true);
                store_iv.setSelected(false);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getResources().getColor(R.color.c_ffc440));
                store_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                myself_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                break;
            case 1:
                home_iv.setSelected(false);
                store_iv.setSelected(true);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                store_tv.setTextColor(getResources().getColor(R.color.c_ffc440));
                myself_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                break;
            case 2:
                home_iv.setSelected(false);
                store_iv.setSelected(false);
                myself_iv.setSelected(true);

                home_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                store_tv.setTextColor(getResources().getColor(R.color.c_ffffff));
                myself_tv.setTextColor(getResources().getColor(R.color.c_ffc440));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
