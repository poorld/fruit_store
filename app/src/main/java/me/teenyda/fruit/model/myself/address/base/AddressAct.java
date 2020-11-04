package me.teenyda.fruit.model.myself.address.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.address.add.AddressAddAct;
import me.teenyda.fruit.model.myself.address.base.adapter.AddressAdapter;
import me.teenyda.fruit.model.myself.address.base.presenter.AddressPresenter;
import me.teenyda.fruit.model.myself.address.base.view.IAddressView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAct extends MvpActivity<IAddressView, AddressPresenter> implements IAddressView {

    @BindView(R.id.address_rv)
    RecyclerView address_rv;
    private AddressAdapter mAddressAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddressAct.class);
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
        return R.layout.act_address;
    }

    @Override
    protected void initView() {
        setTitleShow(true, "收货地址", true);
        setRightTitleClick("添加", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressAddAct.startActivity(getMContext());
            }
        });
        setBack();
        ButterKnife.bind(this);

        mAddressAdapter = new AddressAdapter(getMContext());
        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        address_rv.setLayoutManager(manager);
        address_rv.setAdapter(mAddressAdapter);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        mAddressAdapter.addContacts(contacts);
    }
}
