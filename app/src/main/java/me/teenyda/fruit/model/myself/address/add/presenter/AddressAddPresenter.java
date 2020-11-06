package me.teenyda.fruit.model.myself.address.add.presenter;

import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.myself.address.add.view.IAddressAddView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAddPresenter extends BaseRxPresenter<IAddressAddView> {

    public void addContact(Contact contact) {
        addDisposable(mApi.addContacts(contact), new MyObserver<Contact>(mContext) {
            @Override
            public void onSuccess(Contact contact) {
                mView.addContactSuccess(contact);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
