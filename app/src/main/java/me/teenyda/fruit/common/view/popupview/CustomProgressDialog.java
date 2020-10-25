package me.teenyda.fruit.common.view.popupview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import me.teenyda.fruit.R;

/**
 * author: teenyda
 * date: 2020/9/19
 * description: 通用加载弹窗
 */
public class CustomProgressDialog extends ProgressDialog {

    public static CustomProgressDialog getInstance(Context context, int id) {
        /*if (mDialog == null) {
            synchronized (Object.class) {
                if (mDialog == null) {
                    mDialog = new CustomProgressDialog(context, id);
                }
            }
        }
        return mDialog;*/
        mDialog = new CustomProgressDialog(context, id);
        return mDialog;
    }



    private static CustomProgressDialog mDialog;
    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    private CustomProgressDialog(Context context, int id) {
        super(context);
        this.mContext = context;
        this.mResid = id;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        mDialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mAnimation.start();
                    }
                });
            }
        });
        mDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mAnimation.stop();
                for (int i = 0; i < mAnimation.getNumberOfFrames(); i++) {
                    Drawable frame = mAnimation.getFrame(i);
                    if (frame instanceof BitmapDrawable) {
                        ((BitmapDrawable) frame).getBitmap().recycle();
                    }
                    frame.setCallback(null);
                }
                mAnimation.setCallback(null);
                mAnimation = null;
                mDialog = null;
                System.gc();
            }
        });
    }


    private void initView() {
        setContentView(R.layout.custom_loading);
        mImageView = (ImageView) findViewById(R.id.loading_iv);
    }

}
