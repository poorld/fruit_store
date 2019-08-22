package me.teenyda.mvp_template.model.home.base;

import android.content.Context;

import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.BaseView;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.model.home.base.presenter.HomeP;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFrag extends MvpFragment<IHomeV, HomeP> implements BaseView {

    @Override
    protected HomeP createPresenter() {
        return new HomeP();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_home;
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
