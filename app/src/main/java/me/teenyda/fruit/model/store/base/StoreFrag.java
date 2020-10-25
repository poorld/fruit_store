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
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_store;
    }

    @Override
    protected void initView() {
//        StatusBarUtil.setTranslucentForImageView((Activity) getMContext(), 0, mViewNeedOffset);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
