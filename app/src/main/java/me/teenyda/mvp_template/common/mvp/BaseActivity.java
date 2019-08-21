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
import android.widget.RelativeLayout;

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

        RelativeLayout startbarRL = mView.findViewById(R.id.start_bar);
        if (startbarRL != null) {
            int statusBarHeight = getStatusBarHeight();
            startbarRL.setPadding(0,statusBarHeight,0, 0);
        }
        ActivityManager.getAppManager().addActivity(this);
        viewInitializer();
        doBuseness();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;

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
}
