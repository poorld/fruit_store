package me.teenyda.fruit.model.myself.address.add;

import android.content.Context;
import android.content.Intent;

import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.address.add.presenter.AddressPresenter;
import me.teenyda.fruit.model.myself.address.add.view.IAddressAddView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAddAct extends MvpActivity<IAddressAddView, AddressPresenter> implements IAddressAddView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddressAddAct.class);
        context.startActivity(intent);
    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_address_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
