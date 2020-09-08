package me.teenyda.mvp_template.model.store.base;

import android.app.Activity;
import android.content.Context;

import com.jaeger.library.StatusBarUtil;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.MvpRxFragment;
import me.teenyda.mvp_template.model.store.base.presenter.StoreP;
import me.teenyda.mvp_template.model.store.base.view.IStoreV;

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
