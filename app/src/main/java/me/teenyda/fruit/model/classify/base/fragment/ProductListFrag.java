package me.teenyda.fruit.model.classify.base.fragment;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.ProductCategory;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.model.classify.base.fragment.adapter.ProductListAdapter;
import me.teenyda.fruit.model.classify.base.fragment.presenter.ProductListPresenter;
import me.teenyda.fruit.model.classify.base.fragment.view.IProductListView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductListFrag extends MvpRxFragment<IProductListView, ProductListPresenter> implements IProductListView {

    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private Unbinder binder;

    private ProductCategory mCategory;
    private ProductListAdapter mAdapter;

    public static ProductListFrag getInstance(ProductCategory category) {
        ProductListFrag frag = new ProductListFrag();
        frag.mCategory = category;
        return frag;
    }

    @Override
    protected ProductListPresenter createPresenter() {
        return new ProductListPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.product_list_frag;
    }

    @Override
    protected void initView() {
        binder = ButterKnife.bind(this, mView);

        xrv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new ProductListAdapter(getMContext());
        xrv.setAdapter(mAdapter);
    }

    @Override
    protected void requestData() {
        mPresenter.getProductByCategoryId(mCategory.getProductCategoryId());
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setProductList(List<SimpleProductEntity> productList) {
        mAdapter.addProducts(productList);
    }
}
