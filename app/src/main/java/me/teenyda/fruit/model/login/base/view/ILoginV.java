package me.teenyda.fruit.model.login.base.view;

import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public interface ILoginV extends BaseView {

    void loginSuccess(User user1);
}
