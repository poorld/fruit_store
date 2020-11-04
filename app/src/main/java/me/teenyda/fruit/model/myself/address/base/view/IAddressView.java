package me.teenyda.fruit.model.myself.address.base.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public interface IAddressView extends BaseView {

    void setContacts(List<Contact> contacts);
}
