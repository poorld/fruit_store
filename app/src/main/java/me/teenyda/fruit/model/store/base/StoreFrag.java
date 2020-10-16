package me.teenyda.fruit.model.store.base;

import android.content.Context;

import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.model.store.base.presenter.StoreP;
import me.teenyda.fruit.model.store.base.view.IStoreV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class StoreFrag extends MvpRxFragment<IStoreV, StoreP> implements IStoreV {


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
//        StatusBarUtil.setTranslucentForImageView((Activity) getMContext(), 0, mViewNeedOffset);
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
