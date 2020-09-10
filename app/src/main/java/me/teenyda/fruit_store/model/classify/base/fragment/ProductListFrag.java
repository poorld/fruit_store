package me.teenyda.fruit_store.model.classify.base.fragment;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.classify.base.fragment.adapter.ProductListAdapter;
import me.teenyda.fruit_store.model.classify.base.fragment.presenter.ProductListPresenter;
import me.teenyda.fruit_store.model.classify.base.fragment.view.IProductListView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductListFrag extends MvpRxFragment<IProductListView, ProductListPresenter> implements IProductListView {

    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private Unbinder binder;

    public static ProductListFrag getInstance() {
        ProductListFrag frag = new ProductListFrag();
        return frag;
    }

    @Override
    protected ProductListPresenter createPresenter() {
        return new ProductListPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.product_list_frag;
    }

    @Override
    protected void viewInitializer() {
        binder = ButterKnife.bind(this, mView);

        xrv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        xrv.setAdapter(new ProductListAdapter(getMContext()));
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
