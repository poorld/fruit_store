package me.teenyda.fruit.model.classify.evaluation.presenter;

import me.teenyda.fruit.common.entity.CommentsDto;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.classify.evaluation.view.IEvaluationView;

/**
 * author: teenyda
 * date: 2020/12/26
 * description:
 */
public class EvaluationPresenter extends BaseRxPresenter<IEvaluationView> {

    public void getProduct(Integer productId) {
        addDisposable(mApi.getSimpleProduct(productId), new MyObserver<SimpleProductEntity>(mContext) {
            @Override
            public void onSuccess(SimpleProductEntity simpleProductEntity) {
                mView.setProduct(simpleProductEntity);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void comment(CommentsDto commentsDto) {
        addDisposable(mApi.comments(commentsDto), new MyObserver<CommentsDto>(mContext) {
            @Override
            public void onSuccess(CommentsDto commentsDto) {
                mView.commentSuccess();
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void orderComplete(String orderNum) {
        addDisposable(mApi.complete(orderNum), new MyObserver<OrderInfo>(mContext) {
            @Override
            public void onSuccess(OrderInfo orderInfo) {
                mView.orderCompleteSuccess();
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });

    }
}
