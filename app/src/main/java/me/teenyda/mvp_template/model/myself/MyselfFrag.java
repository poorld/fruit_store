package me.teenyda.mvp_template.model.myself;

import android.content.Context;

import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.model.myself.base.presenter.MyselfP;
import me.teenyda.mvp_template.model.myself.base.view.IMySelfV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class MyselfFrag extends MvpFragment<IMySelfV, MyselfP> implements IMySelfV {
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

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
