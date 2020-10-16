package me.teenyda.fruit.common.view.popupview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.teenyda.fruit.R;
import me.teenyda.fruit.common.utils.ToolUtils;


public class CommonDialog extends Dialog {

    private Context mContext;
    private int mHeight;
    private int mWidth;
    private int mLayoutId;
    private boolean mCancelable;
    private String mTitle;
    private String mMessage;
    private Builder.ConfirmClick mConfirmClick;



    public CommonDialog(Context context, Builder builder) {
        super(context);
        mContext = builder.mContext;
        mHeight = builder.mHeight;
        mWidth = builder.mWidth;
        mLayoutId = builder.mLayoutId;
        mCancelable = builder.mCancelable;

    }

    public CommonDialog(Context context, int themeResId, Builder builder) {
        super(context, themeResId);
        mContext = builder.mContext;
        mHeight = builder.mHeight;
        mWidth = builder.mWidth;
        mLayoutId = builder.mLayoutId;
        mCancelable = builder.mCancelable;
        mTitle = builder.mTitle;
        mMessage = builder.mMessage;
        mConfirmClick = builder.mConfirmClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mLayoutId == 0) {
            return;
        }
        View contentView = LayoutInflater.from(mContext).inflate(mLayoutId, null, false);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(false);
//        ViewGroup.LayoutParams lp = contentView.getLayoutParams(); null
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.height = mHeight != 0 ? mHeight : RelativeLayout.LayoutParams.WRAP_CONTENT;
        lp.width = mWidth != 0 ? mHeight : RelativeLayout.LayoutParams.WRAP_CONTENT;
        contentView.setLayoutParams(lp);
        TextView title = contentView.findViewById(R.id.tv_dialog_title);
        title.setText(mTitle);
        TextView msg = contentView.findViewById(R.id.tv_dialog_msg);
        msg.setText(mMessage);
        RelativeLayout rlLeft = contentView.findViewById(R.id.rl_dialog_left);
        rlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RelativeLayout rlRight = contentView.findViewById(R.id.rl_dialog_right);
        if (rlRight != null) {
            rlRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mConfirmClick.onConfirmClick();
                }
            });
        }

        setContentView(contentView);
    }

    public static class Builder {
        private Context mContext;
        private int mThemeResId = -1;
        private int mHeight;
        private int mWidth;
        private int mLayoutId;
        private boolean mCancelable = false;
        private String mTitle;
        private String mMessage;
        private ConfirmClick mConfirmClick;

        public interface ConfirmClick{
            void onConfirmClick();
        }

        public Builder setConfirmListener(ConfirmClick confirmClick) {
            mConfirmClick = confirmClick;
            return this;
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        public Builder setThemeResId(int themeResId) {
            mThemeResId = themeResId;
            return this;
        }

        public Builder setDpHeight(int height) {
            mHeight = ToolUtils.dip2px(mContext, height);
            return this;
        }

        public Builder setDpWidth(int width) {
            mWidth = ToolUtils.dip2px(mContext, width);
            return this;
        }

        public Builder setLayout(int layoutId) {
            mLayoutId = layoutId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }


        public CommonDialog build() {
            if (mThemeResId != -1) {
                return new CommonDialog(mContext,mThemeResId,  this);
            }
            return new CommonDialog(mContext, new Builder());
        }

    }

}
