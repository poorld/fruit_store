package me.teenyda.mvp_template.common.mvp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Toast;

import me.teenyda.mvp_template.common.net.resp.BaseResponse;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public abstract class MvpActivity<V extends BaseView, P extends BasePresenter<V>> extends BaseActivity implements BaseView {


    public Toast mToast;

    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public void showLoading() {
        showToast("加载弹窗");
    }

    @Override
    public void hideLoading() {
        showToast("隐藏加载");
    }

    @Override
    public void onErrorCode(BaseResponse baseResponse) {
        showToast(baseResponse.getMsg());
    }
}

