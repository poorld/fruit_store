package me.teenyda.fruit.model.myself.address;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.address.presenter.AddressPresenter;
import me.teenyda.fruit.model.myself.address.view.IAddressView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAct extends MvpActivity<IAddressView, AddressPresenter> implements IAddressView {

    @BindView(R.id.address_rv)
    RecyclerView address_rv;

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_address;
    }

    @Override
    protected void initView() {
        setTitleShow(true, "收货地址", false);
        ButterKnife.bind(this);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
