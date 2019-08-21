package me.teenyda.mvp_template.common.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.app.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    protected View mView;

    /**
     * 结束activity
     */
    private static final String ACTION_FINISH_ACTIVITY = "0";

    /**
     * 结束所有acitivity
     */
    private static final String ACTION_FINISH_ALL_ACTIVITY = "1";

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_FINISH_ACTIVITY:
                    ActivityManager.getAppManager().finishActivity(BaseActivity.this);
                    break;
                case ACTION_FINISH_ALL_ACTIVITY:
                    ActivityManager.getAppManager().finishAllActivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseInitializer();
        registerBroadcart();
        mView = LayoutInflater.from(this).inflate(setR_layout(), null);
        setContentView(mView);
        setStatusBar(true);
        ActivityManager.getAppManager().addActivity(this);
        viewInitializer();
        doBuseness();
    }


    /**
     * 状态栏设置
     * @param padding
     */
    protected void setStatusBar(boolean padding) {
        RelativeLayout actionBar = getMyActionBar();
        if (padding && actionBar != null) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
            ViewGroup.LayoutParams layoutParams = actionBar.getLayoutParams();
            layoutParams.height += getStatusBarHeight(this);
            actionBar.setLayoutParams(layoutParams);
            View view = actionBar.getChildAt(0);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(this), 0, 0);

        } else {
            StatusBarUtil.setTranslucent(this);
        }

    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    protected abstract void baseInitializer();

    protected abstract int setR_layout();

    protected abstract void viewInitializer();

    protected abstract void doBuseness();

    private void registerBroadcart() {

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_FINISH_ACTIVITY);
        intentFilter.addAction(ACTION_FINISH_ALL_ACTIVITY);
        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        ActivityManager.getAppManager().finishActivity(this);
    }

    /**
     * 设置点击返回结束activity
     */
    protected void setBack() {
        RelativeLayout actionBar = getMyActionBar();
        if (actionBar != null) {
            actionBar.findViewById(R.id.actionbar_back)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
        }
    }

    /**
     * 设置标题
     * @param title
     */
    protected void setTitle(String title) {
        RelativeLayout myActionBar = getMyActionBar();
        if (myActionBar != null) {
            TextView titleTv = myActionBar.findViewById(R.id.actionbar_title);
            titleTv.setText(title);
        }
    }

    /**
     * 设置右边标题
     * @param rightTitle
     */
    protected void setRightTitle(String rightTitle) {
        RelativeLayout myActionBar = getMyActionBar();
        if (myActionBar != null) {
            TextView titleTv = myActionBar.findViewById(R.id.actionbar_right_title);
            titleTv.setText(rightTitle);
        }
    }

    protected RelativeLayout getMyActionBar() {
        return mView.findViewById(R.id.start_bar);
    }

    protected View $(int id) {
        return mView.findViewById(id);
    }
}
