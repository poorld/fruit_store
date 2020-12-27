package me.teenyda.fruit.model.classify.comments;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.classify.comments.adapter.CommentsAdapter;
import me.teenyda.fruit.model.classify.comments.presenter.CommentsPresenter;
import me.teenyda.fruit.model.classify.comments.view.ICommentsView;

/**
 * author: teenyda
 * date: 2020/12/27
 * description:
 */
public class CommentsActivity extends MvpActivity<ICommentsView, CommentsPresenter> implements ICommentsView {

    @BindView(R.id.comments_rv)
    RecyclerView comments_rv;
    private CommentsAdapter mCommentsAdapter;

    public static void startAct(Context context, Integer productId) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }

    @Override
    protected CommentsPresenter createPresenter() {
        return new CommentsPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_comments;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitleShow(true, "查看评论", false);
        setBack();
        mCommentsAdapter = new CommentsAdapter(getMContext());
        comments_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        comments_rv.setAdapter(mCommentsAdapter);

    }

    @Override
    protected void requestData() {
        int productId = getIntent().getIntExtra("productId", 0);
        mPresenter.getComments(productId);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setComments(List<Comments> comments) {
        mCommentsAdapter.addData(comments);
    }
}
