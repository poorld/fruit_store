package me.teenyda.mvp_template.model.store.base;

import android.content.Context;

import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.model.store.base.presenter.StoreP;
import me.teenyda.mvp_template.model.store.base.view.IStoreV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class StoreFrag extends MvpFragment<IStoreV, StoreP> implements IStoreV {
    @Override
    protected StoreP createPresenter() {
        return new StoreP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_store;
    }

    @Override
    protected void viewInitializer() {

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
