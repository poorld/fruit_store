package me.teenyda.fruit.model.classify.base.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.ProductCategory;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.classify.base.view.IClassifyView;

/**
 * author: teenyda
 * date: 2020/9/9
 * description:
 */
public class ClassifyPresenter extends BaseRxPresenter<IClassifyView> {

    public void getProductCategory() {
        addDisposable(mApi.category(), new MyObserver<List<ProductCategory>>(mContext, true) {
            @Override
            public void onSuccess(List<ProductCategory> categories) {
                mView.setProductCategory(categories);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
