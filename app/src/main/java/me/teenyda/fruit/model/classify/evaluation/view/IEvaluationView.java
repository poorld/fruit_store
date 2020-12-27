package me.teenyda.fruit.model.classify.evaluation.view;

import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/12/26
 * description:
 */
public interface IEvaluationView extends BaseView {

    void setProduct(SimpleProductEntity product);
    void commentSuccess();
    void orderCompleteSuccess();
}
