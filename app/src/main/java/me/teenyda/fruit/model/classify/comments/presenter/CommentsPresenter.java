package me.teenyda.fruit.model.classify.comments.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.model.classify.comments.view.ICommentsView;

/**
 * author: teenyda
 * date: 2020/12/27
 * description:
 */
public class CommentsPresenter extends BaseRxPresenter<ICommentsView> {

    public void getComments(Integer productId) {
        addDisposable(mApi.getComment(productId), new MyObserver<List<Comments>>(mContext) {
            @Override
            public void onSuccess(List<Comments> comments) {
                mView.setComments(comments);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
