package me.teenyda.fruit_store.common.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class BaseRxFragment extends RxFragment {

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
