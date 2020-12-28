package me.teenyda.fruit.model.classify.base.fragment.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.common.net.request.ProductQueryReq;
import me.teenyda.fruit.model.classify.base.fragment.view.IProductListView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductListPresenter extends BaseRxPresenter<IProductListView> {

    public void getProductByCategoryId(Integer categoryId) {
        addDisposable(mApi.productByCategory(categoryId), new MyObserver<List<SimpleProductEntity>>(mContext, false) {
            @Override
            public void onSuccess(List<SimpleProductEntity> result) {
                mView.setProductList(result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getProductByCategoryAndName(ProductQueryReq req) {
        addDisposable(mApi.productByCategoryAndName(req), new MyObserver<List<SimpleProductEntity>>(mContext, false) {
            @Override
            public void onSuccess(List<SimpleProductEntity> result) {
                mView.setProductList(result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
