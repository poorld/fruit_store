package me.teenyda.fruit_store.model.myself;

import android.content.Context;

import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpFragment;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.myself.base.presenter.MyselfP;
import me.teenyda.fruit_store.model.myself.base.view.IMySelfV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class MyselfFrag extends MvpRxFragment<IMySelfV, MyselfP> implements IMySelfV {
    @Override
    protected MyselfP createPresenter() {
        return new MyselfP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_myself;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this, mView);
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
