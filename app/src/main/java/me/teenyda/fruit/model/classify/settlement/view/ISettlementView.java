package me.teenyda.fruit.model.classify.settlement.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Discounts;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public interface ISettlementView extends BaseView {

    void setUserInfo(User userInfo);

    void setDiscount(List<Discounts> discounts);

    void setOrderItem(SettlementOrder orderItem);
}
