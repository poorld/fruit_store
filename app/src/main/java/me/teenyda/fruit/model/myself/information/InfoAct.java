package me.teenyda.fruit.model.myself.information;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.view.popupview.PopupUpdateInfo;
import me.teenyda.fruit.model.myself.information.presenter.InfoPresenter;
import me.teenyda.fruit.model.myself.information.view.IInfoV;

/**
 * author: teenyda
 * date: 2020/9/21
 * description:
 */
public class InfoAct extends MvpActivity<IInfoV, InfoPresenter> implements IInfoV {

    private PopupUpdateInfo mPopupUpdateInfo;

    // @BindView(R.id.info_rl_username)
    // RelativeLayout info_rl_username;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InfoAct.class);
        context.startActivity(intent);
    }

    @Override
    protected InfoPresenter createPresenter() {
        return new InfoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_info;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setStatusBarTran(true, true);
        setBack();
        setTitle("个人信息");
        setRightDisplay(false);

        mPopupUpdateInfo = new PopupUpdateInfo(getMContext());

    }

    @OnClick({R.id.info_rl_username})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_rl_username:
                // 两个都可以用
                mPopupUpdateInfo.show(view);
                // showPurchaseView();
                break;
        }
    }

    private void showPurchaseView() {
        // 以特定的风格创建一个dialog
        Dialog dialog = new Dialog(this,R.style.ShowDialog);
        // 加载dialog布局view
        View purchase = LayoutInflater.from(this).inflate(R.layout.pop_info, null);
        initPurchaseViews(purchase,dialog);
        // 设置外部点击 取消dialog
        dialog.setCancelable(true);
        // 获得窗体对象
        Window window = dialog.getWindow();
        // 设置窗体的对齐方式
        window.setGravity(Gravity.CENTER);
        // 设置窗体动画
        window.setWindowAnimations(R.style.video_popup_toast_anim);
        // 设置窗体的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setContentView(purchase);
        dialog.show();
    }

    private void initPurchaseViews(View purchase, final Dialog dialog) {
        /*purchase.findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        purchase.findViewById(R.id.llInner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }


    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
