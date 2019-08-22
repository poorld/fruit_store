package me.teenyda.mvp_template.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import me.teenyda.mvp_template.common.net.resp.BaseResponse;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public abstract class MvpActivity<V extends BaseView, M, P extends BasePresenter<V, M>> extends BaseActivity implements BaseView {


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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorCode(BaseResponse baseResponse) {

    }
}

