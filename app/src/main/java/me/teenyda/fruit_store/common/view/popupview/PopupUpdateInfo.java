package me.teenyda.fruit_store.common.view.popupview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import me.teenyda.fruit_store.R;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class PopupUpdateInfo {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;

    public PopupUpdateInfo(Context context) {
        mContext = context;
        initPopup();
    }

    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_info, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.video_popup_toast_anim);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });


    }

    public void show(View view) {
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        setAlpha(0.5f);
    }

    public void dismiss() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void setAlpha(float alpha) {
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }

}
