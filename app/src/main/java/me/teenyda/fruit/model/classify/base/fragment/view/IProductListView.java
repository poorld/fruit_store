package me.teenyda.fruit.model.classify.base.fragment.view;

import java.util.List;

import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public interface IProductListView extends BaseView {

    void setProductList(List<SimpleProductEntity> productList);
}
