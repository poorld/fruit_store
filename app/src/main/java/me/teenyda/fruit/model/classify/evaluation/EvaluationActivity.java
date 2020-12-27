package me.teenyda.fruit.model.classify.evaluation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.mvp.MvpActivity;
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




    public static void startActivity(Context context, Integer productId) {
        Intent intent = new Intent(context, EvaluationActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
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

    @Override
    protected void requestData() {
        int productId = getIntent().getIntExtra("productId", 0);
        mPresenter.getProduct(productId);
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
}
