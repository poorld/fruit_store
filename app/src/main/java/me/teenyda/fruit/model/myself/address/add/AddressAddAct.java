package me.teenyda.fruit.model.myself.address.add;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.myself.address.add.presenter.AddressAddPresenter;
import me.teenyda.fruit.model.myself.address.add.view.IAddressAddView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAddAct extends MvpActivity<IAddressAddView, AddressAddPresenter> implements IAddressAddView {

    @BindView(R.id.address_add)
    TextView address_add;

    @BindView(R.id.contact_name)
    EditText contact_name;
    @BindView(R.id.contact_phone)
    EditText contact_phone;
    @BindView(R.id.contact_address)
    EditText contact_address;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddressAddAct.class);
        context.startActivity(intent);
    }

    @Override
    protected AddressAddPresenter createPresenter() {
        return new AddressAddPresenter();
    }

    @Override
    protected void initData() {

    }

    @OnClick({})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address_add:
                Contact contact = getContact();
                if (contact != null) {
                    contact.setUserId(10001);
                    mPresenter.addContact(contact);
                }
                break;
        }
    }

    private Contact getContact() {
        String contactName = contact_name.getText().toString();
        String contactPhone = contact_phone.getText().toString();
        String contactAddress = contact_address.getText().toString();
        if (TextUtils.isEmpty(contactName)) {
            showToast(contact_name.getHint().toString());
            return null;
        }
        if (TextUtils.isEmpty(contactPhone)) {
            showToast(contact_phone.getHint().toString());
            return null;
        }
        if (TextUtils.isEmpty(contactAddress)) {
            showToast(contact_address.getHint().toString());
            return null;
        }
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setMobile(contactPhone);
        contact.setAddress(contactAddress);
        return contact;
    }

    @Override
    protected int setR_layout() {
        return R.layout.act_address_add;
    }

    @Override
    protected void initView() {
        setTitleShow(true, "添加收货地址", false);
        ButterKnife.bind(this);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void addContactSuccess(Contact contact) {
        setResult(RESULT_OK);
        finish();
    }
}
