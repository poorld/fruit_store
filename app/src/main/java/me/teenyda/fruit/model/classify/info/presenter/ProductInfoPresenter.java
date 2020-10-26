package me.teenyda.fruit.model.classify.info.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.Product;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.classify.info.view.IProductInfoView;

/**
 * author: teenyda
 * date: 2020/9/10
 * description:
 */
public class ProductInfoPresenter extends BaseRxPresenter<IProductInfoView> {

    public void getProduct(Integer productId) {
        addDisposable(mApi.getProduct(productId), new MyObserver<Product>(mContext) {
            @Override
            public void onSuccess(Product product) {
                mBaserView.setProduct(product);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getComments(Integer productId) {
        addDisposable(mApi.getBestComment(productId), new MyObserver<Comments>(mContext) {
            @Override
            public void onSuccess(Comments result) {
                mBaserView.setComments(result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
