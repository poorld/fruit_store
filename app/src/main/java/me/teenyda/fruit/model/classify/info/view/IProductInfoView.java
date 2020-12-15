package me.teenyda.fruit.model.classify.info.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.Product;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public interface IProductInfoView extends BaseView {

    void setProduct(Product product);

    void setComments(Comments comments);

    void orderSuccess(OrderItem orderItem);
    void addCartSuccess(OrderItem orderItem);
}
