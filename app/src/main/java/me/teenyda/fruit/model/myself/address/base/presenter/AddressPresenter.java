package me.teenyda.fruit.model.myself.address.base.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.myself.address.base.view.IAddressView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressPresenter extends BaseRxPresenter<IAddressView> {

    public void getContacts(int userId) {
        addDisposable(mApi.getContacts(userId), new MyObserver<List<Contact>>(mContext) {
            @Override
            public void onSuccess(List<Contact> contacts) {
                mView.setContacts(contacts);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
