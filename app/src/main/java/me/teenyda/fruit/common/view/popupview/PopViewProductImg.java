package me.teenyda.fruit.common.view.popupview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.utils.GlideApp;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 图片相框（左右滑动）
 * 左右滑动参考：https://blog.csdn.net/u014646004/article/details/50816275/
 */

public class PopViewProductImg implements View.OnTouchListener {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;

    private ImageView pro_info_img;
    // 下标
    private TextView tv_iamge_index;

    private String res;

    private int index = 0;

    // 布局主容器
    private RelativeLayout rl_photo_frame;

    //最小距离
    private static final int FLING_MIN_DISTANCE = 50;
    //最小速度
    private static final int FLING_MIN_VELOCITY = 0;
    // 左右滑动
    GestureDetector.SimpleOnGestureListener myGestureListener = new GestureDetector.SimpleOnGestureListener(){
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.e("<--滑动测试-->", "开始滑动");
            float x = e1.getX()-e2.getX();
            float x2 = e2.getX()-e1.getX();
            if(x>FLING_MIN_DISTANCE&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
                Toast.makeText(mContext, "向左手势", Toast.LENGTH_SHORT).show();
                // 下一张
                chooseImage(++index);

            }else if(x2>FLING_MIN_DISTANCE&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
                Toast.makeText(mContext, "向右手势", Toast.LENGTH_SHORT).show();
                chooseImage(--index);
            }

            return false;
        };
    };
    private GestureDetector mGestureDetector;
    private List<String> mRes;

    public PopViewProductImg(Context context) {
        mContext = context;

        initPopView();
    }


    private void initPopView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_show_image, null, false);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationZoom);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        mView.findViewById(R.id.img_close)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

        //防止返回时直接返回到上一个activity
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        pro_info_img = mView.findViewById(R.id.pro_info_img);
        tv_iamge_index = mView.findViewById(R.id.tv_iamge_index);
        rl_photo_frame = mView.findViewById(R.id.rl_photo_frame);

        mGestureDetector = new GestureDetector(mContext, myGestureListener);
        rl_photo_frame.setOnTouchListener(this);//将主容器的监听交给本activity，本activity再交给mGestureDetector
        rl_photo_frame.setLongClickable(true);   //必需设置这为true 否则也监听不到手势

        mRes = new ArrayList<>();
        // mRes.add(R.drawable.bg_monkey_king);
        // mRes.add(R.drawable.comments1);
        // mRes.add(R.drawable.comments2);
        // mRes.add(R.drawable.image4);
        // mRes.add(R.drawable.image7);
        // mRes.add(R.drawable.product01);
        // mRes.add(R.drawable.product_info_bg);
    }

    public void addImgsByUrl(List<String> urls) {
        mRes.addAll(urls);
    }



    public void show(View v, int index) {

        chooseImage(index);

        // setFocusable 默认是false
        // setFocusable(true)外部和内部都会响应，点击外部就会取消，setOutsideTouchable(false)失效

        // 设置为true，不然监听不到返回键
        mPopupWindow.setFocusable(true);
        // 设置PopupWindow是否能响应点击事件
        mPopupWindow.setTouchable(true);
        // 设置PopupWindow内容区域外的区域是否响应点击事件（true：响应；false：不响应）
        mPopupWindow.setOutsideTouchable(false);

        mPopupWindow.showAtLocation(v, Gravity.CENTER, 0 , 0);
        backgroundAlpha(0.5f);
    }

    private void chooseImage(int i) {
        index = i;

        // 向左滑，下一张图
        // if (left)
        //     index++;
        // else
        //     index--;


        if (index <= -1)
            index = mRes.size() - 1;
        else if(index >= mRes.size())
            index = 0;

        tv_iamge_index.setText(index + 1 + "/" + mRes.size());

        GlideApp.with(mContext)
                .load(mRes.get(index))
                .override(400, 400)
                .centerCrop()
                .transition(withCrossFade())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        pro_info_img.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }
}
