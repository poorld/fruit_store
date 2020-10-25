package me.teenyda.fruit.common.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit.R;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class BaseRxFragment extends RxFragment {

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();

        mView = LayoutInflater.from(getContext()).inflate(setR_layout(), container, false);

        initView();

        requestData();

        return mView;
    }

    protected abstract void initData();

    protected abstract int setR_layout();

    protected abstract void initView();

    protected abstract void requestData();

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
                StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, actionBar);

        } else if (hasToolBar && !setStatusBarTrans) {
            //例子: SecondKillActivity
            StatusBarUtil.setColor(getActivity(), getContext().getColor(R.color.colorPrimary), 0);
        } else if (!hasToolBar && setStatusBarTrans) {
            //没有状态栏，设置透明
            // 例子：MemberAct（会员）
            StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0,null);
        } else {
            //默认
            StatusBarUtil.setColor(getActivity(), getContext().getColor(R.color.colorPrimary), 0);
        }
    }

    protected void setTitleShow(boolean displayLeft, String title, boolean displayRight) {
        setLeftDisplay(displayLeft);
        setTitle(title);
        setRightDisplay(displayLeft);
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
        TextView title = getRightTitle();
        if (title != null)
            title.setText(rightTitle);
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

    private RelativeLayout getMyActionBar() {
        return mView.findViewById(R.id.start_bar);
    }
}
