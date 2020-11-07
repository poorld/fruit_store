package me.teenyda.fruit.model.myself.address.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.view.popupview.CommonPopView;
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

    private CommonPopView mCommonPopView;

    public static final int RES_CODE_ADDRESS_ADD = 10390;

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
                // AddressAddAct.startActivity(getMContext());
                Intent intent = new Intent(getMContext(), AddressAddAct.class);
                startActivityForResult(intent, RES_CODE_ADDRESS_ADD);
            }
        });
        setBack();
        ButterKnife.bind(this);

        mAddressAdapter = new AddressAdapter(getMContext());
        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        address_rv.setLayoutManager(manager);
        address_rv.setAdapter(mAddressAdapter);

        mCommonPopView = new CommonPopView(getMContext());
        mAddressAdapter.setContactAction(new AddressAdapter.IContactAction() {
            @Override
            public void onContactSelect(Contact contact) {
                // 修改
            }

            @Override
            public void onContactDelete(int contactId) {
                mCommonPopView.setTitle("删除地址")
                        .setLeftTitle("取消")
                        .setRightTitle("确定")
                        .setMessage("确定删除该联系地址？")
                        .setOnBtnClick(new CommonPopView.OnBtnClick() {
                            @Override
                            public void onLeftClick() {
                                mCommonPopView.dismiss();
                            }

                            @Override
                            public void onRightClick() {
                                mPresenter.deleteContact(contactId);
                            }
                        }).show(address_rv);
            }
        });
    }

    @Override
    protected void requestData() {
        mPresenter.getContacts(10001);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        mAddressAdapter.addContacts(contacts);
    }

    @Override
    public void delContacts() {
        requestData();
        mCommonPopView.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode){
            if (requestCode == RES_CODE_ADDRESS_ADD){
                requestData();
            }
        }
    }
}
