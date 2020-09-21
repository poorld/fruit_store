package me.teenyda.fruit_store.common.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.app.ActivityManager;
import me.teenyda.fruit_store.model.main.MainActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

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
        //默认是有工具栏(Toolbar)，状态栏颜色是主题颜色
        setStatusBarTran(true, false);
        ActivityManager.getAppManager().addActivity(this);
        viewInitializer();
        doBuseness();
    }


    /**
     * 状态栏设置透明
     * @param hasToolBar 是否有工具栏
     * @param setStatusBarTrans 是否设置透明
     * 使用指南：
     * 默认为有工具栏，工具栏背景颜色要设置成colorPrimary，状态栏颜色为colorPrimary
     * 使用setStatusBarTran(true, false);设置默认
     *
     * 当使用透明工具栏，inclued_statusbar_transp_black或者inclued_statusbar_transp_white
     * 使用setStatusBarTran(true, true)可以让状态栏透明
     *
     * 当没有状态栏时，让状态栏透明
     * 使用setStatusBarTran(false, true)可以让状态栏透明
     */
    protected void setStatusBarTran(boolean hasToolBar, boolean setStatusBarTrans) {
        RelativeLayout actionBar = getMyActionBar();
        // 如果有状态栏
        if (hasToolBar && setStatusBarTrans) {
            if (actionBar != null)
                //例子：WalletAct（钱包） 使用透明状态栏
                StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, actionBar);

        } else if (hasToolBar && !setStatusBarTrans){
            //例子: SecondKillActivity
            StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 0);
        } else if (!hasToolBar && setStatusBarTrans){
            //没有状态栏，设置透明
            // 例子：MemberAct（会员）
            StatusBarUtil.setTranslucent(this, 0);
        }else {
            //默认
            StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 0);
        }


        /*RelativeLayout actionBar = getMyActionBar();
        if (setStatusBar && actionBar != null) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
            ViewGroup.LayoutParams layoutParams = actionBar.getLayoutParams();
            layoutParams.height += getStatusBarHeight(this);
            actionBar.setLayoutParams(layoutParams);
            View view = actionBar.getChildAt(0);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(this), 0, 0);

        } else {
//            StatusBarUtil.setTranslucent(this);
//            StatusBarUtil.setTranslucentForImageView(this,0, null);
            StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 0);
            // StatusBarUtil.setTranslucent(this, 0);
        }*/

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

    protected void setTitleShow(boolean displayLeft, String title, boolean displayRight) {
        setLeftDisplay(displayLeft);
        setTitle(title);
        setRightDisplay(displayLeft);
    }


    private TextView getRightTitle() {
        RelativeLayout myActionBar = getMyActionBar();
        if (myActionBar != null) {
            TextView titleTv = myActionBar.findViewById(R.id.actionbar_right_title);
            return titleTv;
        }
        return null;
    }

    protected void setLeftDisplay(boolean display) {
        RelativeLayout myActionBar = getMyActionBar();
        if (myActionBar != null) {
            RelativeLayout rl = myActionBar.findViewById(R.id.actionbar_back);
            rl.setVisibility(display ? View.VISIBLE : View.INVISIBLE);
        }
    }
    protected void setRightDisplay(boolean display) {
        RelativeLayout myActionBar = getMyActionBar();
        if (myActionBar != null) {
            RelativeLayout rl = myActionBar.findViewById(R.id.rl_right);
            rl.setVisibility(display ? View.VISIBLE : View.INVISIBLE);
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
