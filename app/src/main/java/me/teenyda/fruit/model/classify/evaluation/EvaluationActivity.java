package me.teenyda.fruit.model.classify.evaluation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.CommentsDto;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.FileCacheUtil;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.model.classify.evaluation.presenter.EvaluationPresenter;
import me.teenyda.fruit.model.classify.evaluation.view.IEvaluationView;

/**
 * author: teenyda
 * date: 2020/12/26
 * description:
 */
public class EvaluationActivity extends MvpActivity<IEvaluationView, EvaluationPresenter> implements IEvaluationView {

    @BindView(R.id.eval_product_img)
    ImageView eval_product_img;

    @BindView(R.id.eval_product_name)
    TextView eval_product_name;

    @BindView(R.id.eval_et)
    EditText eval_et;

    @BindView(R.id.eval_tv)
    TextView eval_tv;
    private int mProductId;


    public static void startActivity(Context context, Integer productId, String orderNum) {
        Intent intent = new Intent(context, EvaluationActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("orderNum", orderNum);
        ((Activity)context).startActivityForResult(intent, 0);
    }

    @Override
    protected EvaluationPresenter createPresenter() {
        return new EvaluationPresenter();
    }

    @Override
    protected void initData() {
        
    }

    @Override
    protected int setR_layout() {
        return R.layout.act_evaluation;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitleShow(true, "评价", false);
        setBack();
    }

    @OnClick({R.id.eval_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eval_tv:
                comments();
                break;
        }
    }

    private void comments() {
        String content = eval_et.getText().toString();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Integer userId = FileCacheUtil.getUser(getMContext()).getUserId();
        CommentsDto comments = new CommentsDto();
        comments.setUserId(userId);
        comments.setProductId(mProductId);
        comments.setContent(content);
        mPresenter.comment(comments);
    }

    @Override
    protected void requestData() {
        mProductId = getIntent().getIntExtra("productId", 0);
        mPresenter.getProduct(mProductId);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setProduct(SimpleProductEntity product) {
        eval_product_name.setText(product.getName());
        GlideApp.with(this)
                .load(product.getDefaultImg())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        eval_product_img.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    public void commentSuccess() {
        showToast("评论完成!");
        String orderNum = getIntent().getStringExtra("orderNum");
        mPresenter.orderComplete(orderNum);
    }

    @Override
    public void orderCompleteSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
