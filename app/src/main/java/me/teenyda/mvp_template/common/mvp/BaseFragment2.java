package me.teenyda.mvp_template.common.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class BaseFragment2 extends RxFragment {

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseInitializer();

        mView = LayoutInflater.from(getContext()).inflate(setR_layout(), container, false);

        viewInitializer();

        doBuseness();

        return mView;
    }

    protected abstract void baseInitializer();

    protected abstract int setR_layout();

    protected abstract void viewInitializer();

    protected abstract void doBuseness();
}
