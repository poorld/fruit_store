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
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.teenyda.fruit_store.R;


public class PopViewProductImg {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;

    private ImageView pro_info_img;

    private String res;

    private int flag = 0;

    public PopViewProductImg(Context context) {
        mContext = context;

        initPopView();
    }


    private void initPopView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_show_image, null, false);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

        pro_info_img = mView.findViewById(R.id.pro_info_img);

    }



    public void show(View v, int drawableId) {
        List<Integer> res = new ArrayList<>();
        res.add(R.drawable.bg_monkey_king);
        res.add(R.drawable.comments1);
        res.add(R.drawable.comments2);
        res.add(R.drawable.image4);
        res.add(R.drawable.image7);
        res.add(R.drawable.product01);
        res.add(R.drawable.product_info_bg);

        Glide.with(mContext)
                .load(res.get(flag++))
                .into(pro_info_img);

        if (flag == res.size())
            flag = 0;


        // setFocusable 默认是false
        // setFocusable(true)外部和内部都会响应，点击外部就会取消，setOutsideTouchable(false)失效
        mPopupWindow.setFocusable(false);
        // 设置PopupWindow是否能响应点击事件
        mPopupWindow.setTouchable(true);
        // 设置PopupWindow内容区域外的区域是否响应点击事件（true：响应；false：不响应）
        mPopupWindow.setOutsideTouchable(false);

        mPopupWindow.showAtLocation(v, Gravity.CENTER, 0 , 0);
        backgroundAlpha(0.5f);
    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }
}
