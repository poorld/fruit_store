package me.teenyda.fruit.model.classify.info.presenter;

import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.OrderItem;
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

    /**
     * 获取产品
     * @param productId
     */
    public void getProduct(Integer productId) {
        addDisposable(mApi.getProduct(productId), new MyObserver<Product>(mContext) {
            @Override
            public void onSuccess(Product product) {
                mView.setProduct(product);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    /**
     * 获取评论
     * @param productId
     */
    public void getComments(Integer productId) {
        addDisposable(mApi.getBestComment(productId), new MyObserver<Comments>(mContext) {
            @Override
            public void onSuccess(Comments result) {
                mView.setComments(result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    /**
     * 下订单
     * @param orderItem
     */
    public void order(OrderItem orderItem) {
        addDisposable(mApi.order(orderItem), new MyObserver<OrderItem>(mContext, true) {
            @Override
            public void onSuccess(OrderItem orderItem1) {
                mView.orderSuccess(orderItem1);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
