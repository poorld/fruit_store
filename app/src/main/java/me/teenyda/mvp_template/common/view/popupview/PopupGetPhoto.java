package me.teenyda.mvp_template.common.view.popupview;

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

import me.teenyda.mvp_template.R;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class PopupGetPhoto {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;
    private GetPhotoListener mGetPhotoListener;

    public PopupGetPhoto(Context context) {
        mContext = context;
        initPopup();
    }

    public interface GetPhotoListener{
        void takePhoto();

        /**
         * 从相册
         */
        void fromAlbum();
    }

    public void setPhotoListener(GetPhotoListener getPhotoListener) {
        mGetPhotoListener = getPhotoListener;
    }

    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.popup_get_photo, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomInAndOut);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        mView.findViewById(R.id.photo_cancel_rl)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
        mView.findViewById(R.id.take_photo_rl)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mGetPhotoListener != null) {
                            mGetPhotoListener.takePhoto();
                        }
                    }
                });
        mView.findViewById(R.id.from_album_rl)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mGetPhotoListener != null) {
                            mGetPhotoListener.fromAlbum();
                        }
                    }
                });
    }

    public void show(View view) {
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
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
