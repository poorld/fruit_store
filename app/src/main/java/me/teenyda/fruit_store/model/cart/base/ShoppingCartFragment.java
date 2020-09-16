package me.teenyda.fruit_store.model.cart.base;

import android.content.Context;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.model.cart.base.adapter.ShoppingCartAdapter;
import me.teenyda.fruit_store.model.cart.base.presenter.ShoppingCartPresenter;
import me.teenyda.fruit_store.model.cart.base.view.IShoppingCartView;
import me.teenyda.fruit_store.model.main.MainActivity;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class ShoppingCartFragment extends MvpRxFragment<IShoppingCartView, ShoppingCartPresenter> implements IShoppingCartView {

    @BindView(R.id.shopping_cart_rv)
    XRecyclerView shopping_cart_rv;

    @BindView(R.id.start_bar)
    RelativeLayout start_bar;


    @Override
    protected ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_shopping_cart;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this, mView);

        setTitleShow(false, "购物车", false);

        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), start_bar);

        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        shopping_cart_rv.setLayoutManager(manager);
        shopping_cart_rv.setAdapter(new ShoppingCartAdapter(getMContext()));

        shopping_cart_rv.setNestedScrollingEnabled(false);

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }
}
