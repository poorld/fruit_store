package me.teenyda.fruit.common.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.teenyda.fruit.common.net.resp.BaseResponse;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class MvpRxFragment<V extends BaseView, P extends BaseRxPresenter>
        extends BaseRxFragment implements BaseView {

    public Toast mToast;

    protected P mPresenter;

    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
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

    protected void startActivity(Class<?> clazz) {
        Context context = getContext();
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
