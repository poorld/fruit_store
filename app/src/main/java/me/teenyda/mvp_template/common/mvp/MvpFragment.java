package me.teenyda.mvp_template.common.mvp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import me.teenyda.mvp_template.common.net.resp.BaseResponse;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class MvpFragment<V extends BaseView, P extends BasePresenter>
        extends BaseFragment implements BaseView {

    public Toast mToast;

    protected P mPresenter;

    protected abstract P createPresenter();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public void onErrorCode(BaseResponse baseResponse) {
        showToast(baseResponse.getMsg());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
