package me.teenyda.fruit.model.home.new_fruit;

import android.content.Context;
import android.content.Intent;

import com.youth.banner.transformer.ZoomOutPageTransformer;

import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.home.new_fruit.adapter.NewFruitAdapter;
import me.teenyda.fruit.model.home.new_fruit.presenter.NewFruitPresenter;
import me.teenyda.fruit.model.home.new_fruit.view.INewFruitView;

/**
 * author: teenyda
 * date: 2020/9/20
 * description:
 */
public class NewFruit2Activity extends MvpActivity<INewFruitView, NewFruitPresenter> implements INewFruitView {


    @BindView(R.id.view_pager)
    ViewPager2 viewPager;

    private NewFruitAdapter pagerAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NewFruit2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected NewFruitPresenter createPresenter() {
        return new NewFruitPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_new_fruit;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setStatusBarTran(true, true);
        setBack();
        setRightDisplay(false);
        setTitle("");

        pagerAdapter = new NewFruitAdapter(this, DataBean.getNewFruitData());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
