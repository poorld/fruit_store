package me.teenyda.fruit.model.classify.comments.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2020/12/27
 * description:
 */
public interface ICommentsView extends BaseView {

    void setComments(List<Comments> comments);
}
