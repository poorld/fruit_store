package me.teenyda.fruit_store.common.mvp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public abstract class BaseFragment extends Fragment {

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
