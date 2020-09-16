package me.teenyda.fruit_store.common.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit_store.R;

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
        baseInitializer();

        mView = LayoutInflater.from(getContext()).inflate(setR_layout(), container, false);

        viewInitializer();

        doBuseness();

        return mView;
    }

    protected abstract void baseInitializer();

    protected abstract int setR_layout();

    protected abstract void viewInitializer();

    protected abstract void doBuseness();

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
