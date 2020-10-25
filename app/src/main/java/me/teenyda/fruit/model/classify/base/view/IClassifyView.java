package me.teenyda.fruit.model.classify.base.view;

import java.util.List;

import me.teenyda.fruit.common.entity.ProductCategory;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/9/9
 * description:
 */
public interface IClassifyView extends BaseView {

    void setProductCategory(List<ProductCategory> categorys);
}
